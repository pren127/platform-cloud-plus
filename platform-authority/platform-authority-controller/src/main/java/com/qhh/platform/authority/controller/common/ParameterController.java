package com.qhh.platform.authority.controller.common;


import com.qhh.basic.annotation.security.PreAuth;
import com.qhh.basic.base.controller.SuperController;
import com.qhh.platform.authority.dto.common.ParameterPageQuery;
import com.qhh.platform.authority.dto.common.ParameterSaveDTO;
import com.qhh.platform.authority.dto.common.ParameterUpdateDTO;
import com.qhh.platform.authority.entity.common.Parameter;
import com.qhh.platform.authority.service.common.ParameterService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 参数配置
 * </p>
 *
 * @author qhh
 * @date 2020-02-05
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/parameter")
@Api(value = "Parameter", tags = "参数配置")
@PreAuth(replace = "authority:parameter:")
public class ParameterController extends SuperController<ParameterService, Long, Parameter, ParameterPageQuery, ParameterSaveDTO, ParameterUpdateDTO> {

}
