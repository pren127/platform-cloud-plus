package com.qhh.platform.tenant.strategy.impl;

import com.google.common.collect.Sets;
import com.qhh.basic.database.mybatis.conditions.Wraps;
import com.qhh.basic.utils.BizAssert;
import com.qhh.basic.utils.CollHelper;
import com.qhh.platform.common.constant.BizConstant;
import com.qhh.platform.tenant.dto.TenantConnectDTO;
import com.qhh.platform.tenant.entity.DatasourceConfig;
import com.qhh.platform.tenant.entity.TenantDatasourceConfig;
import com.qhh.platform.tenant.enumeration.TenantConnectTypeEnum;
import com.qhh.platform.tenant.service.DatasourceConfigService;
import com.qhh.platform.tenant.service.InitDsService;
import com.qhh.platform.tenant.service.TenantDatasourceConfigService;
import com.qhh.platform.tenant.strategy.InitSystemStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化系统
 * <p>
 * 初始化规则：
 * platform-authority-server/src/main/resources/sql 路径存放8个sql文件 (每个库对应一个文件)
 * platform_base.sql            # 基础库：权限、消息，短信，邮件，文件等
 * data_platform_base.sql       # 基础库数据： 如初始用户，初始角色，初始菜单
 *
 * @author qhh
 * @date 2019/10/25
 */
@Service("DATASOURCE")
@Slf4j
@RequiredArgsConstructor
public class DatasourceInitSystemStrategy implements InitSystemStrategy {

    private final DatasourceConfigService datasourceConfigService;
    private final TenantDatasourceConfigService tenantDatasourceConfigService;
    private final InitDsService initDsService;

    /**
     * 求优化！
     *
     * @param tenantConnect 链接信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initConnect(TenantConnectDTO tenantConnect) {
        Map<String, DatasourceConfig> typeMap = new HashMap<>(8);
        if (TenantConnectTypeEnum.REMOTE.eq(tenantConnect.getConnectType())) {
            Long authorityDatasource = tenantConnect.getAuthorityDatasource();
            BizAssert.notNull(authorityDatasource, "权限服务数据源不能为空");
            Long fileDatasource = tenantConnect.getFileDatasource();
            fileDatasource = fileDatasource == null ? authorityDatasource : fileDatasource;
            Long msgsDatasource = tenantConnect.getMsgDatasource();
            msgsDatasource = msgsDatasource == null ? authorityDatasource : msgsDatasource;
            Long oauthDatasource = tenantConnect.getOauthDatasource();
            oauthDatasource = oauthDatasource == null ? authorityDatasource : oauthDatasource;
            Long gateDatasource = tenantConnect.getGateDatasource();
            gateDatasource = gateDatasource == null ? authorityDatasource : gateDatasource;
            List<DatasourceConfig> dcList = datasourceConfigService.listByIds(Sets.newHashSet(authorityDatasource, fileDatasource, msgsDatasource, oauthDatasource, gateDatasource));
            dcList.forEach(item -> {
                item.setType(tenantConnect.getConnectType());
                item.setPoolName(tenantConnect.getTenant());
            });
            Map<Long, DatasourceConfig> dcMap = CollHelper.uniqueIndex(dcList, DatasourceConfig::getId, (data) -> data);

            DatasourceConfig authorityDc = dcMap.get(authorityDatasource);
            typeMap.put(BizConstant.AUTHORITY, authorityDc);
            typeMap.put(BizConstant.FILE, dcMap.getOrDefault(fileDatasource, authorityDc));
            typeMap.put(BizConstant.MSG, dcMap.getOrDefault(msgsDatasource, authorityDc));
            typeMap.put(BizConstant.OAUTH, dcMap.getOrDefault(oauthDatasource, authorityDc));
            typeMap.put(BizConstant.GATE, dcMap.getOrDefault(gateDatasource, authorityDc));
            typeMap.put(BizConstant.BASE_EXECUTOR, dcMap.getOrDefault(gateDatasource, authorityDc));
            typeMap.put(BizConstant.EXTEND_EXECUTOR, dcMap.getOrDefault(gateDatasource, authorityDc));

            tenantDatasourceConfigService.remove(Wraps.<TenantDatasourceConfig>lbQ().eq(TenantDatasourceConfig::getTenantId, tenantConnect.getId()));

            List<TenantDatasourceConfig> list = new ArrayList<>();
            list.add(TenantDatasourceConfig.builder().application(BizConstant.AUTHORITY).tenantId(tenantConnect.getId()).datasourceConfigId(authorityDatasource).build());
            list.add(TenantDatasourceConfig.builder().application(BizConstant.FILE).tenantId(tenantConnect.getId()).datasourceConfigId(fileDatasource).build());
            list.add(TenantDatasourceConfig.builder().application(BizConstant.MSG).tenantId(tenantConnect.getId()).datasourceConfigId(msgsDatasource).build());
            list.add(TenantDatasourceConfig.builder().application(BizConstant.OAUTH).tenantId(tenantConnect.getId()).datasourceConfigId(oauthDatasource).build());
            list.add(TenantDatasourceConfig.builder().application(BizConstant.GATE).tenantId(tenantConnect.getId()).datasourceConfigId(gateDatasource).build());
            list.add(TenantDatasourceConfig.builder().application(BizConstant.BASE_EXECUTOR).tenantId(tenantConnect.getId()).datasourceConfigId(gateDatasource).build());
            list.add(TenantDatasourceConfig.builder().application(BizConstant.EXTEND_EXECUTOR).tenantId(tenantConnect.getId()).datasourceConfigId(gateDatasource).build());
            tenantDatasourceConfigService.saveBatch(list);
        } else {
            String tenant = tenantConnect.getTenant();
            DatasourceConfig dto = new DatasourceConfig();
            dto.setType(tenantConnect.getConnectType());
            dto.setPoolName(tenant);

            typeMap.put(BizConstant.AUTHORITY, dto);
            typeMap.put(BizConstant.FILE, dto);
            typeMap.put(BizConstant.MSG, dto);
            typeMap.put(BizConstant.OAUTH, dto);
            typeMap.put(BizConstant.GATE, dto);
            typeMap.put(BizConstant.BASE_EXECUTOR, dto);
            typeMap.put(BizConstant.EXTEND_EXECUTOR, dto);
        }

        // 动态初始化数据源
        return initDsService.initConnect(typeMap);
    }

    @Override
    public boolean reset(String tenant) {

        return true;
    }

    @Override
    public boolean delete(List<Long> ids, List<String> tenantCodeList) {
        if (tenantCodeList.isEmpty()) {
            return true;
        }
        tenantDatasourceConfigService.remove(Wraps.<TenantDatasourceConfig>lbQ().in(TenantDatasourceConfig::getTenantId, ids));

        tenantCodeList.forEach(initDsService::removeDataSource);
        return true;
    }
}
