package com.qhh.platform.oauth.api.hystrix;

import com.qhh.platform.oauth.api.DictionaryApi;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * 数据字典项 查询
 *
 * @author qhh
 * @date 2019/07/26
 */
@Component
public class DictionaryApiFallback implements DictionaryApi {
    @Override
    public Map<Serializable, Object> findNameByIds(Set<Serializable> ids) {
        return Collections.emptyMap();
    }

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return Collections.emptyMap();
    }
}
