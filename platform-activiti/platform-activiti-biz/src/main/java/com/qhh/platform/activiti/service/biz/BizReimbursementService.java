package com.qhh.platform.activiti.service.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qhh.basic.base.request.PageParams;
import com.qhh.basic.base.service.SuperService;
import com.qhh.platform.activiti.domain.core.UpdateCollEntity;
import com.qhh.platform.activiti.dto.biz.BizReimbursementPageDTO;
import com.qhh.platform.activiti.dto.biz.BizReimbursementResDTO;
import com.qhh.platform.activiti.entity.biz.BizReimbursement;

/**
 * <p>
 * 业务接口
 * 报销流程
 * </p>
 *
 * @author qhh
 * @date 2020-08-31
 */
public interface BizReimbursementService extends SuperService<BizReimbursement> {

    /**
     * 业务key
     *
     * @return 业务key
     */
    String getKey();

    /**
     * 保存
     *
     * @param bizReimbursement 报销
     * @return 是否成功
     */
    Boolean saveBiz(BizReimbursement bizReimbursement);

    /**
     * 删除
     *
     * @param entity id
     * @return 是否成功
     */
    Boolean deleteBiz(UpdateCollEntity<String> entity);

    /**
     * 分页
     *
     * @param params 分页参数
     * @return 分页数据
     */
    IPage<BizReimbursementResDTO> pageBiz(PageParams<BizReimbursementPageDTO> params);
}
