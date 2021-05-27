package com.qhh.platform.gateway.fallback;


import com.qhh.basic.base.R;
import com.qhh.basic.exception.code.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 响应超时熔断处理器
 *
 * @author qhh
 */
@RestController
@Slf4j
public class FallbackController {

    @RequestMapping("/fallback")
    public Mono<R> fallback() {
        log.info("fallback");
        return Mono.just(R.fail(ExceptionCode.SYSTEM_TIMEOUT));
    }
}
