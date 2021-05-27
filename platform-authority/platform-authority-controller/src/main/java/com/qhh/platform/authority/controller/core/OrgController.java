package com.qhh.platform.authority.controller.core;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.qhh.basic.annotation.log.SysLog;
import com.qhh.basic.annotation.security.PreAuth;
import com.qhh.basic.base.R;
import com.qhh.basic.base.controller.SuperCacheController;
import com.qhh.basic.database.mybatis.conditions.Wraps;
import com.qhh.basic.utils.BeanPlusUtil;
import com.qhh.basic.utils.BizAssert;
import com.qhh.basic.utils.TreeUtil;
import com.qhh.platform.authority.dto.core.OrgPageQuery;
import com.qhh.platform.authority.dto.core.OrgSaveDTO;
import com.qhh.platform.authority.dto.core.OrgUpdateDTO;
import com.qhh.platform.authority.entity.core.Org;
import com.qhh.platform.authority.service.core.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.qhh.basic.utils.StrPool.DEF_PARENT_ID;
import static com.qhh.basic.utils.StrPool.DEF_ROOT_PATH;
import static com.qhh.basic.utils.StrPool.EMPTY;
import static com.qhh.platform.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static com.qhh.platform.common.constant.SwaggerConstants.DATA_TYPE_STRING;
import static com.qhh.platform.common.constant.SwaggerConstants.PARAM_TYPE_QUERY;


/**
 * <p>
 * 前端控制器
 * 组织
 * </p>
 *
 * @author qhh
 * @date 2019-07-22
 */
@Slf4j
@RestController
@RequestMapping("/org")
@Api(value = "Org", tags = "组织")
@PreAuth(replace = "authority:org:")
public class OrgController extends SuperCacheController<OrgService, Long, Org, OrgPageQuery, OrgSaveDTO, OrgUpdateDTO> {
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "name", value = "名称", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "检测名称是否可用", notes = "检测名称是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam(required = false) Long id, @RequestParam String name) {
        return success(baseService.check(id, name));
    }


    @Override
    public R<Org> handlerSave(OrgSaveDTO model) {
        Org org = BeanPlusUtil.toBean(model, Org.class);
        fillOrg(org);
        this.baseService.save(org);
        return success(org);
    }

    @Override
    public R<Org> handlerUpdate(OrgUpdateDTO model) {
        Org org = BeanPlusUtil.toBean(model, Org.class);
        fillOrg(org);
        this.baseService.updateAllById(org);
        return success(org);
    }

    private void fillOrg(Org org) {
        if (org.getParentId() == null || org.getParentId() <= 0) {
            org.setParentId(DEF_PARENT_ID);
            org.setTreePath(EMPTY);
        } else {
            Org parent = this.baseService.getByIdCache(org.getParentId());
            BizAssert.notNull(parent, "父组织不能为空");

            org.setTreePath(StrUtil.join(DEF_ROOT_PATH, parent.getTreePath(), parent.getId()));
        }
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        return this.success(baseService.remove(ids));
    }


    /**
     * 查询系统所有的组织树
     *
     * @param state 状态
     * @author qhh
     * @date 2019-07-29 11:59
     */
    @ApiOperation(value = "查询系统所有的组织树", notes = "查询系统所有的组织树")
    @GetMapping("/tree")
    @PreAuth("hasAnyPermission('{}view')")
    @SysLog("查询系统所有的组织树")
    public R<List<Org>> tree(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "state", required = false) Boolean state) {
        List<Org> list = this.baseService.list(Wraps.<Org>lbQ()
                .like(Org::getLabel, name).eq(Org::getState, state).orderByAsc(Org::getSortValue));
        return this.success(TreeUtil.buildTree(list));
    }


    @Override
    public R<Boolean> handlerImport(List<Map<String, String>> list) {
        List<Org> userList = list.stream().map((map) -> {
            Org item = new Org();
            item.setDescribe(map.getOrDefault("描述", EMPTY));
            item.setLabel(map.getOrDefault("名称", EMPTY));
            item.setAbbreviation(map.getOrDefault("简称", EMPTY));
            item.setState(Convert.toBool(map.getOrDefault("状态", EMPTY)));
            return item;
        }).collect(Collectors.toList());

        return R.success(baseService.saveBatch(userList));
    }

}
