package com.qhh.platform.tenant.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author qhh
 * @date 2021/1/4 10:45 下午
 */
@FeignClient(name = "ExtendExecutorDsApi", url = "${platform.feign.executor-server:http://127.0.0.1:8774}", path = "/platform-extend-executor/ds")
public interface ExtendExecutorDsApi extends DsApi {

}
