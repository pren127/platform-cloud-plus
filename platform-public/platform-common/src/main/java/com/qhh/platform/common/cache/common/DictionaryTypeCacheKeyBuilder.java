package com.qhh.platform.common.cache.common;


import com.qhh.basic.cache.model.CacheKeyBuilder;
import com.qhh.platform.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * <p>
 * key={tenant}:dictionary_type:{type}
 * field1: {code} --> name
 * field2: {code} --> name
 *
 * <p>
 * #c_dictionary_item
 *
 * @author qhh
 * @date 2020/9/20 6:45 下午
 */
public class DictionaryTypeCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.DICTIONARY_TYPE;
    }


}
