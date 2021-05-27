package com.qhh.platform.common.cache.auth;

import com.qhh.basic.cache.model.CacheKeyBuilder;
import com.qhh.platform.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 资源 KEY
 * <p>
 * #c_role_authority
 *
 * @author qhh
 * @date 2020/9/20 6:45 下午
 */
public class RoleResourceCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.ROLE_RESOURCE;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }


}
