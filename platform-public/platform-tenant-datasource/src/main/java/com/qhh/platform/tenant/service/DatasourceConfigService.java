package com.qhh.platform.tenant.service;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.qhh.basic.base.service.SuperService;
import com.qhh.platform.tenant.entity.DatasourceConfig;

/**
 * <p>
 * 业务接口
 * 数据源
 * </p>
 *
 * @author qhh
 * @date 2020-08-21
 */
public interface DatasourceConfigService extends SuperService<DatasourceConfig> {

    /**
     * 测试数据源链接
     *
     * @param dataSourceProperty 数据源信息
     * @return
     */
    Boolean testConnection(DataSourceProperty dataSourceProperty);

}
