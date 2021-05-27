package com.qhh.platform.tenant.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.qhh.platform.tenant.dao.InitDatabaseMapper;
import com.qhh.platform.tenant.entity.DatasourceConfig;
import com.qhh.platform.tenant.enumeration.TenantConnectTypeEnum;
import com.qhh.platform.tenant.enumeration.TenantStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qhh
 * @date 2020/12/30 8:57 下午
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class DatasourceInitDataSourceService {

    private final InitDatabaseMapper initDatabaseMapper;
    private final DataSourceService dataSourceService;
    @Value("${spring.application.name:platform-authority-server}")
    private String applicationName;

    /**
     * 启动项目时，调用初始化数据源
     *
     * @return
     */
    @DS("master")
    public boolean initDataSource() {
        // LOCAL 类型的数据源初始化
        List<String> list = initDatabaseMapper.selectTenantCodeList(TenantStatusEnum.NORMAL.name(), TenantConnectTypeEnum.LOCAL.name());
        list.forEach(dataSourceService::addLocalDynamicRoutingDataSource);

        // REMOTE 类型的数据源初始化
        List<DatasourceConfig> dcList = initDatabaseMapper.listByApplication(applicationName, TenantStatusEnum.NORMAL.name(), TenantConnectTypeEnum.REMOTE.name());
        dcList.forEach(dc -> {
            // 权限服务
            DataSourceProperty dataSourceProperty = new DataSourceProperty();
            BeanUtils.copyProperties(dc, dataSourceProperty);
            dataSourceService.addDynamicRoutingDataSource(dataSourceProperty);
        });
        log.debug("初始化租户数据源成功");
        return true;
    }
}
