package com.qhh.platform.common.cache;


import com.qhh.basic.cache.model.CacheKeyBuilder;

import java.time.Duration;

/**
 * 短信验证码 KEY
 *
 * @author qhh
 * @date 2020/9/23 9:10 上午
 */
public class VerificationCodeCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.REGISTER_USER;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofSeconds(5 * 60);
    }
}
