package com.qhh.platform.activiti.service.biz.impl;


import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.ImmutableMap;
import com.qhh.basic.base.R;
import com.qhh.basic.base.entity.SuperEntity;
import com.qhh.basic.base.service.SuperServiceImpl;
import com.qhh.basic.context.ContextUtil;
import com.qhh.basic.database.mybatis.conditions.Wraps;
import com.qhh.basic.database.mybatis.conditions.update.LbuWrapper;
import com.qhh.basic.utils.BeanPlusUtil;
import com.qhh.basic.utils.CollHelper;
import com.qhh.platform.activiti.constant.LeaveVarConstant;
import com.qhh.platform.activiti.constant.ResultConstant;
import com.qhh.platform.activiti.dao.biz.BizItemMapper;
import com.qhh.platform.activiti.dao.biz.BizLeaveMapper;
import com.qhh.platform.activiti.dao.biz.BizReimbursementMapper;
import com.qhh.platform.activiti.dto.biz.BizItemResDTO;
import com.qhh.platform.activiti.entity.biz.BizItem;
import com.qhh.platform.activiti.entity.biz.BizLeave;
import com.qhh.platform.activiti.entity.biz.BizReimbursement;
import com.qhh.platform.activiti.service.activiti.MyProcessInstantService;
import com.qhh.platform.activiti.service.activiti.MyTaskService;
import com.qhh.platform.activiti.service.biz.BizItemService;
import com.qhh.platform.authority.api.UserBizApi;
import com.qhh.platform.authority.entity.auth.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * <p>
 * </p>
 *
 * @author qhh
 * @date 2020-08-19
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BizItemServiceImpl extends SuperServiceImpl<BizItemMapper, BizItem> implements BizItemService {
    private final MyTaskService myTaskService;
    private final MyProcessInstantService myProcessInstantService;
    private final BizLeaveMapper bizLeaveMapper;
    private final BizReimbursementMapper reimbursementMapper;
    private final UserBizApi userBizApi;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveItem(BizItem po) {
        saveOrUpdate(po);
        String taskId = po.getTaskId();
        Map<String, Object> map = new LinkedHashMap<>();
        // 审批通过
        String result;
        if (po.getResult()) {
            result = ResultConstant.PASS;
        } else {
            result = ResultConstant.REJECT;
        }

        map.put(LeaveVarConstant.RESULT_MSG, ContextUtil.getName() + "【" + result + "】" + po.getItemRemake());
        myTaskService.setVariablesLocal(taskId, map);
        myTaskService.setVariables(taskId, LeaveVarConstant.RESULT, result);
        myTaskService.complete(taskId);

        // 判断流程是否结束
        Boolean over = myProcessInstantService.isOver(po.getInstId());
        if (over) {
            BizLeave bizLeave = BizLeave.builder().id(po.getBizId()).isOver(true).build();
            bizLeaveMapper.updateById(bizLeave);
            reimbursementMapper.updateById(BizReimbursement.builder().id(po.getBizId()).isOver(true).build());
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BizItemResDTO> find(String instId) {
        LbuWrapper<BizItem> wrapper = Wraps.<BizItem>lbU().eq(BizItem::getInstId, instId).orderByDesc(BizItem::getCreateTime);
        List<BizItem> items = list(wrapper);
        List<BizItemResDTO> list = BeanPlusUtil.toBeanList(items, BizItemResDTO.class);

        List<Long> userIds = list.stream().map(SuperEntity::getCreatedBy).collect(Collectors.toList());

        R<List<User>> users = userBizApi.findUserById(userIds);
        if (CollUtil.isNotEmpty(users.getData())) {
            List<User> data = users.getData();
            list.forEach(inst -> data.forEach(user -> {
                if (user.getId().equals(inst.getCreatedBy())) {
                    inst.setCUser(user);
                }
            }));
        }
        return list;
    }

    /**
     * 转换
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<Serializable, BizItem> findItemByTaskId(Set<Serializable> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyMap();
        }

        // 1. 根据 字典编码查询可用的字典列表
        LbuWrapper<BizItem> wrapper = Wraps.<BizItem>lbU().in(BizItem::getTaskId, ids);
        List<BizItem> list = list(wrapper);

        // 2. 将 list 转换成 Map，Map的key是字典编码，value是字典名称
        ImmutableMap<String, BizItem> typeMap = CollHelper.uniqueIndex(list,
                BizItem::getTaskId
                , (item) -> item);

        // 3. 将 Map<String, String> 转换成 Map<Serializable, Object>
        Map<Serializable, BizItem> typeCodeNameMap = new HashMap<>(CollHelper.initialCapacity(typeMap.size()));
        typeMap.forEach(typeCodeNameMap::put);
        return typeCodeNameMap;
    }
}
