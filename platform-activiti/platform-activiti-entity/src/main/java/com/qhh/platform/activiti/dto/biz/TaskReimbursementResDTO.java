package com.qhh.platform.activiti.dto.biz;

import com.qhh.basic.annotation.echo.Echo;
import com.qhh.basic.model.RemoteData;
import com.qhh.platform.activiti.dto.activiti.TaskResDTO;
import com.qhh.platform.activiti.entity.biz.BizReimbursement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 活动任务返回实体
 *
 * @author qhh
 * @date 2020-08-07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TaskReimbursementResDTO extends TaskResDTO {

    /**
     * 对应业务实例
     */
    @Echo(api = "bizReimbursementServiceImpl", method = "findBizByInstId")
    private RemoteData<String, BizReimbursement> biz;

}
