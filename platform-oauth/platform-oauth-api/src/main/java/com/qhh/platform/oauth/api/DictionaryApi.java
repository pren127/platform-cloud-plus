package com.qhh.platform.oauth.api;

import com.qhh.basic.model.LoadService;
import com.qhh.platform.oauth.api.hystrix.DictionaryApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 数据字典API
 *
 * @author qhh
 * @date 2019/07/26
 */
@FeignClient(name = "${platform.feign.oauth-server:platform-oauth-server}", path = "dictionary",
        qualifier = "dictionaryApi", fallback = DictionaryApiFallback.class)
public interface DictionaryApi extends LoadService {
    /**
     * 根据id查询 显示名
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return
     */
    @Override
    @GetMapping("/findNameByIds")
    Map<Serializable, Object> findNameByIds(@RequestParam(value = "ids") Set<Serializable> ids);

    /**
     * 根据id查询实体
     *
     * @param ids 唯一键（可能不是主键ID)
     * @return
     */
    @Override
    @GetMapping("/findByIds")
    Map<Serializable, Object> findByIds(@RequestParam(value = "ids") Set<Serializable> ids);

}
