package com.qhh.platform.gateway.config;

import com.qhh.basic.boot.config.BaseConfig;
import com.qhh.platform.gateway.filter.gateway.PreCheckFilter;
import com.qhh.platform.gateway.service.BlockListService;
import com.qhh.platform.gateway.service.RateLimiterService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qhh
 * @date 2017-12-15 14:42
 */
@Configuration
public class GatewayWebConfiguration extends BaseConfig {

    /**
     * platform-gateway-server 服务在配置文件中配置 platform.webmvc.enabled=false ，会加载下面1个Bean
     */
    @ConditionalOnProperty(prefix = "platform.webmvc", name = "enabled", havingValue = "false", matchIfMissing = true)
    public static class WebfluxConfig {
        /**
         * gateway服务 限流 + 阻止访问 功能的过滤器
         *
         * @param blockListService   阻止列表Service （spring自动注入）
         * @param rateLimiterService 限流Service （spring自动注入）
         */
        @Bean
        public PreCheckFilter getPreCheckFilter(BlockListService blockListService, RateLimiterService rateLimiterService) {
            return new PreCheckFilter(blockListService, rateLimiterService);
        }
    }
}
