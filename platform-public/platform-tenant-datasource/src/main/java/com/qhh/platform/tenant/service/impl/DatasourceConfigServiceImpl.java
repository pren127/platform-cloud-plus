package com.qhh.platform.tenant.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.qhh.basic.base.service.SuperServiceImpl;
import com.qhh.platform.tenant.dao.DatasourceConfigMapper;
import com.qhh.platform.tenant.entity.DatasourceConfig;
import com.qhh.platform.tenant.service.DataSourceService;
import com.qhh.platform.tenant.service.DatasourceConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 数据源
 * </p>
 *
 * @author qhh
 * @date 2020-08-21
 */
@Slf4j
@Service
@DS("master")
@RequiredArgsConstructor
public class DatasourceConfigServiceImpl extends SuperServiceImpl<DatasourceConfigMapper, DatasourceConfig> implements DatasourceConfigService {

    private final DataSourceService dataSourceService;

    @Override
    public Boolean testConnection(DataSourceProperty dataSourceProperty) {
        return dataSourceService.testConnection(dataSourceProperty);
    }

}
