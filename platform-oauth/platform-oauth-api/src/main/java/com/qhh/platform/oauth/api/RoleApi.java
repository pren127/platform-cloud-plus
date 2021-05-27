package com.qhh.platform.oauth.api;

import com.qhh.basic.base.R;
import com.qhh.platform.oauth.api.hystrix.RoleApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 角色API
 *
 * @author qhh
 * @date 2019/08/02
 */
@FeignClient(name = "${platform.feign.oauth-server:platform-oauth-server}", path = "/role", fallback = RoleApiFallback.class)
public interface RoleApi {
    /**
     * 根据角色编码，查找用户id
     *
     * @param codes 角色编码
     * @return 用户id
     */
    @GetMapping("/codes")
    R<List<Long>> findUserIdByCode(@RequestParam(value = "codes") String[] codes);
}
