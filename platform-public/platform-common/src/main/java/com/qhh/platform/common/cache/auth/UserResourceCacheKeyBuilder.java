package com.qhh.platform.common.cache.auth;

import com.qhh.basic.cache.model.CacheKeyBuilder;
import com.qhh.platform.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 用户资源 KEY
 * <p>
 * #c_role_authority & #c_user_role
 *
 * @author qhh
 * @date 2020/9/20 6:45 下午
 */
public class UserResourceCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.USER_RESOURCE;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(12);
    }
}
