package com.qhh.platform.activiti.dto.biz;

import com.qhh.basic.annotation.echo.Echo;
import com.qhh.basic.model.RemoteData;
import com.qhh.platform.activiti.dto.activiti.TaskHiResDTO;
import com.qhh.platform.activiti.entity.biz.BizReimbursement;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class TaskHiReimbursementResDTO extends TaskHiResDTO {

    /**
     * 对应业务实例
     */
    @Echo(api = "bizReimbursementServiceImpl", method = "findBizByInstId")
    protected RemoteData<String, BizReimbursement> biz;


}
