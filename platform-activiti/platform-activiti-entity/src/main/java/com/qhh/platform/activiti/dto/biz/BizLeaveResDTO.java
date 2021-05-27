package com.qhh.platform.activiti.dto.biz;

import com.qhh.platform.activiti.entity.biz.BizLeave;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 实体类
 * 请假流程
 * </p>
 *
 * @author qhh
 * @since 2020-08-20
 */
@Data
@ApiModel(value = "BizLeaveRes", description = "请假流程返回复合实体")
public class BizLeaveResDTO extends BizLeave {

}
