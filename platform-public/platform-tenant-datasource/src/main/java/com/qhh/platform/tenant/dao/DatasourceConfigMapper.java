package com.qhh.platform.tenant.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.qhh.basic.base.mapper.SuperMapper;
import com.qhh.platform.tenant.entity.DatasourceConfig;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 数据源
 * </p>
 *
 * @author qhh
 * @date 2020-08-21
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface DatasourceConfigMapper extends SuperMapper<DatasourceConfig> {

}
