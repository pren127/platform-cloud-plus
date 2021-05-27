package com.qhh.platform.file.config;

import com.qhh.basic.boot.config.BaseConfig;
import com.qhh.basic.log.event.SysLogListener;
import com.qhh.platform.oauth.api.LogApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qhh
 * @date 2017-12-15 14:42
 */
@Configuration
public class FileWebConfiguration extends BaseConfig {
    /**
     * platform.log.enabled = true 并且 platform.log.type=DB时实例该类
     */
    @Bean
    @ConditionalOnExpression("${platform.log.enabled:true} && 'DB'.equals('${platform.log.type:LOGGER}')")
    public SysLogListener sysLogListener(LogApi logApi) {
        return new SysLogListener(logApi::save);
    }
}
