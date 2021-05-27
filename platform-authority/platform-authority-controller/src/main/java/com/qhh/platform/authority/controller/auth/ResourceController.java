package com.qhh.platform.authority.controller.auth;

import com.qhh.basic.annotation.security.PreAuth;
import com.qhh.basic.base.R;
import com.qhh.basic.base.controller.SuperCacheController;
import com.qhh.basic.utils.BeanPlusUtil;
import com.qhh.platform.authority.dto.auth.ResourceSaveDTO;
import com.qhh.platform.authority.dto.auth.ResourceUpdateDTO;
import com.qhh.platform.authority.entity.auth.Resource;
import com.qhh.platform.authority.service.auth.ResourceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 资源
 * </p>
 *
 * @author qhh
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/resource")
@Api(value = "Resource", tags = "资源")
@PreAuth(replace = "authority:resource:")
public class ResourceController extends SuperCacheController<ResourceService, Long, Resource, Resource, ResourceSaveDTO, ResourceUpdateDTO> {
    @Override
    public R<Resource> handlerSave(ResourceSaveDTO data) {
        Resource resource = BeanPlusUtil.toBean(data, Resource.class);
        baseService.saveWithCache(resource);
        return success(resource);
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        return success(baseService.removeByIdWithCache(ids));
    }

    @Override
    public R<Resource> handlerUpdate(ResourceUpdateDTO data) {
        Resource resource = BeanPlusUtil.toBean(data, Resource.class);
        baseService.updateById(resource);
        return success(resource);
    }


}
