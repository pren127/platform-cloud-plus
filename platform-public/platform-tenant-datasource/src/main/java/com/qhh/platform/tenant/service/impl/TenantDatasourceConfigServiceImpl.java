package com.qhh.platform.tenant.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.qhh.basic.base.service.SuperServiceImpl;
import com.qhh.platform.tenant.dao.TenantDatasourceConfigMapper;
import com.qhh.platform.tenant.entity.TenantDatasourceConfig;
import com.qhh.platform.tenant.service.TenantDatasourceConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 租户数据源关系
 *
 * @author qhh
 * @date 2020/8/27 下午4:51
 */
@Slf4j
@Service
@DS("master")
public class TenantDatasourceConfigServiceImpl extends SuperServiceImpl<TenantDatasourceConfigMapper, TenantDatasourceConfig> implements TenantDatasourceConfigService {
}
