package com.qhh.platform.activiti.dto.biz;

import com.qhh.platform.activiti.entity.biz.BizReimbursement;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 实体类
 * 报销流程
 * </p>
 *
 * @author qhh
 * @since 2020-08-20
 */
@Data
@ApiModel(value = "BizReimbursementRes", description = "报销流程返回复合实体")
public class BizReimbursementResDTO extends BizReimbursement {

}
