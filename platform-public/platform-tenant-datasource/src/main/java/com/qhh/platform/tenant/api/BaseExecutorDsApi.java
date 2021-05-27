package com.qhh.platform.tenant.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author qhh
 * @date 2021/1/4 10:45 下午
 */
@FeignClient(name = "BaseExecutorDsApi", url = "${platform.feign.executor-server:http://127.0.0.1:8776}", path = "/platform-base-executor/ds")
public interface BaseExecutorDsApi extends DsApi {

}
