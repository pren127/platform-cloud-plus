package com.qhh.platform.tenant.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.qhh.basic.base.mapper.SuperMapper;
import com.qhh.platform.tenant.entity.TenantDatasourceConfig;
import org.springframework.stereotype.Repository;

/**
 * 租户数据源关系 Mapper
 *
 * @author qhh
 * @date 2020/8/27 下午4:48
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface TenantDatasourceConfigMapper extends SuperMapper<TenantDatasourceConfig> {
}
