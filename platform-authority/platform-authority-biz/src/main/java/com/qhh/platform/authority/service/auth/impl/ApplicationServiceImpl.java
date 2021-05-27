package com.qhh.platform.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.qhh.basic.base.service.SuperCacheServiceImpl;
import com.qhh.basic.cache.model.CacheKey;
import com.qhh.basic.cache.model.CacheKeyBuilder;
import com.qhh.basic.database.mybatis.conditions.Wraps;
import com.qhh.basic.database.mybatis.conditions.query.LbqWrapper;
import com.qhh.platform.authority.dao.auth.ApplicationMapper;
import com.qhh.platform.authority.entity.auth.Application;
import com.qhh.platform.authority.service.auth.ApplicationService;
import com.qhh.platform.common.cache.auth.ApplicationCacheKeyBuilder;
import com.qhh.platform.common.cache.auth.ApplicationClientCacheKeyBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * <p>
 * 业务实现类
 * 应用
 * </p>
 *
 * @author qhh
 * @date 2019-12-15
 */
@Slf4j
@Service
@DS("#thread.tenant")
public class ApplicationServiceImpl extends SuperCacheServiceImpl<ApplicationMapper, Application> implements ApplicationService {

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new ApplicationCacheKeyBuilder();
    }

    @Override
    public Application getByClient(String clientId, String clientSecret) {
        LbqWrapper<Application> wrapper = Wraps.<Application>lbQ()
                .select(Application::getId).eq(Application::getClientId, clientId).eq(Application::getClientSecret, clientSecret);
        Function<CacheKey, Object> loader = k -> super.getObj(wrapper, Convert::toLong);
        CacheKey cacheKey = new ApplicationClientCacheKeyBuilder().key(clientId, clientSecret);
        return getByKey(cacheKey, loader);
    }

}
