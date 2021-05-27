package com.qhh.platform.oauth.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.qhh.basic.base.BaseEnum;
import com.qhh.basic.base.R;
import com.qhh.basic.database.mybatis.auth.DataScopeType;
import com.qhh.basic.utils.CollHelper;
import com.qhh.platform.authority.entity.common.Dictionary;
import com.qhh.platform.authority.enumeration.auth.ApplicationAppTypeEnum;
import com.qhh.platform.authority.enumeration.auth.AuthorizeType;
import com.qhh.platform.authority.enumeration.auth.Sex;
import com.qhh.platform.authority.enumeration.common.LogType;
import com.qhh.platform.authority.service.common.DictionaryService;
import com.qhh.platform.authority.service.common.ParameterService;
import com.qhh.platform.common.enums.HttpMethod;
import com.qhh.platform.file.enumeration.DataType;
import com.qhh.platform.msg.enumeration.MsgBizType;
import com.qhh.platform.msg.enumeration.MsgType;
import com.qhh.platform.oauth.controller.model.Option;
import com.qhh.platform.sms.enumeration.ProviderType;
import com.qhh.platform.sms.enumeration.SendStatus;
import com.qhh.platform.sms.enumeration.SourceType;
import com.qhh.platform.sms.enumeration.TaskStatus;
import com.qhh.platform.tenant.enumeration.TenantConnectTypeEnum;
import com.qhh.platform.tenant.enumeration.TenantStatusEnum;
import com.qhh.platform.tenant.enumeration.TenantTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 通用 控制器
 *
 * @author qhh
 * @date 2019/07/25
 */
@Slf4j
@RestController
@Api(value = "Common", tags = "通用Controller")
@AllArgsConstructor
public class OauthGeneralController {
    private final DictionaryService dictionaryService;
    private final ParameterService parameterService;

    private static final Map<String, Map<String, String>> ENUM_MAP = new HashMap<>(21);
    private static final Map<String, List<Option>> ENUM_LIST_MAP = new HashMap<>(21);

    static {
        // 权限服务
        ENUM_LIST_MAP.put(HttpMethod.class.getSimpleName(), mapOptions(HttpMethod.values()));
        ENUM_LIST_MAP.put(DataScopeType.class.getSimpleName(), mapOptions(DataScopeType.values()));
        ENUM_LIST_MAP.put(LogType.class.getSimpleName(), mapOptions(LogType.values()));
        ENUM_LIST_MAP.put(AuthorizeType.class.getSimpleName(), mapOptions(AuthorizeType.values()));
        ENUM_LIST_MAP.put(Sex.class.getSimpleName(), mapOptions(Sex.values()));
        ENUM_LIST_MAP.put(TenantTypeEnum.class.getSimpleName(), mapOptions(TenantTypeEnum.values()));
        ENUM_LIST_MAP.put(TenantStatusEnum.class.getSimpleName(), mapOptions(TenantStatusEnum.values()));
        ENUM_LIST_MAP.put(ApplicationAppTypeEnum.class.getSimpleName(), mapOptions(ApplicationAppTypeEnum.values()));
        // 租户服务
        ENUM_LIST_MAP.put(TenantConnectTypeEnum.class.getSimpleName(), mapOptions(TenantConnectTypeEnum.values()));
        // 文件服务
        ENUM_LIST_MAP.put(DataType.class.getSimpleName(), mapOptions(HttpMethod.values()));
        //消息服务
        ENUM_LIST_MAP.put(MsgType.class.getSimpleName(), mapOptions(MsgType.values()));
        ENUM_LIST_MAP.put(MsgBizType.class.getSimpleName(), mapOptions(MsgBizType.values()));
        ENUM_LIST_MAP.put(ProviderType.class.getSimpleName(), mapOptions(ProviderType.values()));
        ENUM_LIST_MAP.put(SourceType.class.getSimpleName(), mapOptions(SourceType.values()));
        ENUM_LIST_MAP.put(SendStatus.class.getSimpleName(), mapOptions(SendStatus.values()));
        ENUM_LIST_MAP.put(TaskStatus.class.getSimpleName(), mapOptions(TaskStatus.values()));

        // 权限服务
        ENUM_MAP.put(HttpMethod.class.getSimpleName(), CollHelper.getMap(HttpMethod.values()));
        ENUM_MAP.put(DataScopeType.class.getSimpleName(), CollHelper.getMap(DataScopeType.values()));
        ENUM_MAP.put(LogType.class.getSimpleName(), CollHelper.getMap(LogType.values()));
        ENUM_MAP.put(AuthorizeType.class.getSimpleName(), CollHelper.getMap(AuthorizeType.values()));
        ENUM_MAP.put(Sex.class.getSimpleName(), CollHelper.getMap(Sex.values()));
        ENUM_MAP.put(TenantTypeEnum.class.getSimpleName(), CollHelper.getMap(TenantTypeEnum.values()));
        ENUM_MAP.put(TenantStatusEnum.class.getSimpleName(), CollHelper.getMap(TenantStatusEnum.values()));
        ENUM_MAP.put(ApplicationAppTypeEnum.class.getSimpleName(), CollHelper.getMap(ApplicationAppTypeEnum.values()));
        // 租户服务
        ENUM_MAP.put(TenantConnectTypeEnum.class.getSimpleName(), CollHelper.getMap(TenantConnectTypeEnum.values()));
        // 文件服务
        ENUM_MAP.put(DataType.class.getSimpleName(), CollHelper.getMap(HttpMethod.values()));
        //消息服务
        ENUM_MAP.put(MsgType.class.getSimpleName(), CollHelper.getMap(MsgType.values()));
        ENUM_MAP.put(MsgBizType.class.getSimpleName(), CollHelper.getMap(MsgBizType.values()));
        ENUM_MAP.put(ProviderType.class.getSimpleName(), CollHelper.getMap(ProviderType.values()));
        ENUM_MAP.put(SourceType.class.getSimpleName(), CollHelper.getMap(SourceType.values()));
        ENUM_MAP.put(SendStatus.class.getSimpleName(), CollHelper.getMap(SendStatus.values()));
        ENUM_MAP.put(TaskStatus.class.getSimpleName(), CollHelper.getMap(TaskStatus.values()));
    }

    private static List<Option> mapOptions(BaseEnum[] values) {
        return Arrays.stream(values).map(item -> Option.builder().label(item.getDesc())
                .text(item.getDesc()).value(item.getCode()).build()).collect(Collectors.toList());
    }

    @ApiOperation(value = "获取当前系统指定枚举 Map", notes = "获取当前系统指定枚举")
    @PostMapping("/enums")
    public R<Map<String, Map<String, String>>> enums(@RequestBody String[] codes) {
        if (ArrayUtil.isEmpty(codes)) {
            return R.success(ENUM_MAP);
        }
        Map<String, Map<String, String>> map = new HashMap<>(CollHelper.initialCapacity(codes.length));

        for (String code : codes) {
            if (ENUM_MAP.containsKey(code)) {
                map.put(code, ENUM_MAP.get(code));
            }
        }
        return R.success(map);
    }

    @ApiOperation(value = "获取当前系统指定枚举 List", notes = "获取当前系统指定枚举")
    @PostMapping("/enumLists")
    public R<Map<String, List<Option>>> enumLists(@RequestBody String[] codes) {
        if (ArrayUtil.isEmpty(codes)) {
            return R.success(ENUM_LIST_MAP);
        }
        Map<String, List<Option>> map = new HashMap<>(CollHelper.initialCapacity(codes.length));

        for (String code : codes) {
            if (ENUM_MAP.containsKey(code)) {
                map.put(code, ENUM_LIST_MAP.get(code));
            }
        }
        return R.success(map);
    }

    @ApiOperation(value = "根据类型编码查询字典项", notes = "根据类型编码查询字典项")
    @PostMapping("/dictionary/codes")
    public R<Map<String, List<Dictionary>>> list(@RequestBody String[] types) {
        return R.success(this.dictionaryService.listByTypes(types));
    }

    private static Map<String, List<Option>> mapOptionByDict(Map<String, List<Dictionary>> map) {
        if (CollUtil.isEmpty(map)) {
            return Collections.emptyMap();
        }
        Map<String, List<Option>> newMap = new HashMap<>();
        map.forEach((key, values) -> {
            newMap.put(key, values.stream().map(item -> Option.builder().label(item.getName())
                    .text(item.getName()).value(item.getCode()).build()).collect(Collectors.toList()));
        });
        return newMap;
    }

    @ApiOperation(value = "根据类型编码查询字典项", notes = "根据类型编码查询字典项")
    @PostMapping("/dictionary/codeList")
    public R<Map<String, List<Option>>> codeList(@RequestBody String[] types) {
        return R.success(mapOptionByDict(dictionaryService.listByTypes(types)));
    }


    @GetMapping("/parameter/value")
    public R<String> getValue(@RequestParam(value = "key") String key, @RequestParam(value = "defVal") String defVal) {
        return R.success(parameterService.getValue(key, defVal));
    }
}

