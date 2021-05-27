package com.qhh.platform.activiti.controller.biz;

import com.qhh.basic.annotation.security.PreAuth;
import com.qhh.basic.base.R;
import com.qhh.basic.base.controller.SuperController;
import com.qhh.basic.database.mybatis.conditions.Wraps;
import com.qhh.basic.database.mybatis.conditions.query.QueryWrap;
import com.qhh.platform.activiti.dto.biz.BizItemPageDTO;
import com.qhh.platform.activiti.dto.biz.BizItemResDTO;
import com.qhh.platform.activiti.dto.biz.BizItemSaveDTO;
import com.qhh.platform.activiti.dto.biz.BizItemUpdateDTO;
import com.qhh.platform.activiti.entity.biz.BizItem;
import com.qhh.platform.activiti.service.biz.BizItemService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 前端控制器
 *
 * </p>
 *
 * @author qhh
 * @date 2020-08-19
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/bizItem")
@Api(value = "BizItem", tags = "")
@PreAuth(replace = "bizItem:")
public class BizItemController extends SuperController<BizItemService, Long, BizItem, BizItemPageDTO, BizItemSaveDTO, BizItemUpdateDTO> {

    /**
     * 根据任务id获取事项详情
     *
     */
    @GetMapping(value = "/get")
    public R<BizItem> get(@RequestParam(value = "taskId") String taskId) {
        QueryWrap<BizItem> wrapper = Wraps.q(BizItem.builder().taskId(taskId).build());
        BizItem entity = baseService.getOne(wrapper, false);
        return R.success(entity);
    }

    /**
     * 根据实例id查询该任务的历史审批情况
     *
     */
    @GetMapping(value = "/find")
    public R<List<BizItemResDTO>> find(@RequestParam(value = "instId") String instId) {
        List<BizItemResDTO> list = baseService.find(instId);
        return R.success(list);
    }


    /**
     * 事项审批
     *
     */
    @PostMapping(value = "/save")
    public R<BizItem> saveItem(@RequestBody BizItem po) {
        baseService.saveItem(po);

        return R.success(po);
    }

}
