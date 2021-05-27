package com.qhh.platform.authority.controller.common;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qhh.basic.annotation.security.PreAuth;
import com.qhh.basic.base.R;
import com.qhh.basic.base.controller.SuperController;
import com.qhh.basic.base.request.PageParams;
import com.qhh.basic.database.mybatis.conditions.Wraps;
import com.qhh.basic.database.mybatis.conditions.query.QueryWrap;
import com.qhh.platform.authority.dto.common.DictionaryPageQuery;
import com.qhh.platform.authority.dto.common.DictionarySaveDTO;
import com.qhh.platform.authority.dto.common.DictionaryTypeSaveDTO;
import com.qhh.platform.authority.dto.common.DictionaryUpdateDTO;
import com.qhh.platform.authority.entity.common.Dictionary;
import com.qhh.platform.authority.service.common.DictionaryService;
import com.qhh.platform.common.constant.DefValConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * 字典类型
 * </p>
 *
 * @author qhh
 * @date 2019-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/dictionary")
@Api(value = "Dictionary", tags = "字典类型")
@PreAuth(replace = "authority:dictionary:")
@RequiredArgsConstructor
public class DictionaryController
        extends SuperController<DictionaryService, Long, Dictionary, DictionaryPageQuery, DictionarySaveDTO, DictionaryUpdateDTO> {

    @Override
    public QueryWrap<Dictionary> handlerWrapper(Dictionary model, PageParams<DictionaryPageQuery> params) {
        QueryWrap<Dictionary> qw = Wraps.q(null, params.getExtra(), getEntityClass());
        qw.lambda().eq(Dictionary::getType, model.getType())
                .like(Dictionary::getCode, model.getCode())
                .like(Dictionary::getName, model.getName())
                .ne(Dictionary::getCode, DefValConstants.DICT_PLACEHOLDER);
        return qw;
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        this.baseService.removeByIds(ids);
        return this.success(true);
    }

    @ApiOperation(value = "分页列表查询-字典类型")
    @PostMapping(value = "/pageType")
    @PreAuth("hasAnyPermission('{}view')")
    public R<IPage<Dictionary>> pageType(@RequestBody @Validated PageParams<DictionaryPageQuery> params) {
        IPage<Dictionary> page = params.buildPage();
        baseService.pageType(page, params.getModel());
        return R.success(page);
    }

    @ApiOperation(value = "保存-字典类型")
    @PostMapping(value = "/type")
    @PreAuth("hasAnyPermission('{}add')")
    public R<Dictionary> saveType(@RequestBody @Validated DictionaryTypeSaveDTO dictType) {
        return R.success(baseService.saveType(dictType));
    }

    @ApiOperation(value = "修改-字典类型")
    @PutMapping(value = "/type")
    @PreAuth("hasAnyPermission('{}edit')")
    public R<Boolean> updateType(@RequestBody @Validated DictionaryTypeSaveDTO dictType) {
        return R.success(baseService.updateType(dictType));
    }

    @ApiOperation(value = "删除-字典类型")
    @DeleteMapping(value = "/type")
    @PreAuth("hasAnyPermission('{}delete')")
    public R<Boolean> deleteType(@RequestParam("types[]") List<String> types) {
        return R.success(baseService.deleteType(types));
    }
}
