package com.qhh.platform.authority.controller.auth;


import cn.hutool.core.util.RandomUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.qhh.basic.annotation.security.PreAuth;
import com.qhh.basic.base.R;
import com.qhh.basic.base.controller.SuperCacheController;
import com.qhh.platform.authority.dto.auth.ApplicationPageQuery;
import com.qhh.platform.authority.dto.auth.ApplicationSaveDTO;
import com.qhh.platform.authority.dto.auth.ApplicationUpdateDTO;
import com.qhh.platform.authority.entity.auth.Application;
import com.qhh.platform.authority.service.auth.ApplicationService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 应用
 * </p>
 *
 * @author qhh
 * @date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/application")
@Api(value = "Application", tags = "应用")
@ApiSupport(author = "zuihou")
@PreAuth(replace = "authority:application:")
public class ApplicationController extends SuperCacheController<ApplicationService, Long, Application, ApplicationPageQuery, ApplicationSaveDTO, ApplicationUpdateDTO> {

    @Override
    public R<Application> handlerSave(ApplicationSaveDTO applicationSaveDTO) {
        applicationSaveDTO.setClientId(RandomUtil.randomString(24));
        applicationSaveDTO.setClientSecret(RandomUtil.randomString(32));
        return super.handlerSave(applicationSaveDTO);
    }

}
