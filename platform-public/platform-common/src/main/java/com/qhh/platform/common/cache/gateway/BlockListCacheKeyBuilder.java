package com.qhh.platform.common.cache.gateway;

import com.qhh.basic.cache.model.CacheKeyBuilder;
import com.qhh.platform.common.cache.CacheKeyDefinition;

/**
 * 阻止列表 KEY
 * <p>
 *
 * @author qhh
 * @date 2020/9/20 6:45 下午
 */
public class BlockListCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.BLOCKLIST;
    }

}
