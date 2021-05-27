package com.qhh.platform.file.storage;

import com.qhh.platform.file.properties.FileServerProperties;
import com.qhh.platform.file.service.AttachmentService;
import com.qhh.platform.file.strategy.FileChunkStrategy;
import com.qhh.platform.file.strategy.FileStrategy;
import com.qhh.platform.file.strategy.impl.local.LocalFileChunkStrategyImpl;
import com.qhh.platform.file.strategy.impl.local.LocalFileStrategyImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 本地上传配置
 *
 * @author qhh
 * @date 2019/06/18
 */

@EnableConfigurationProperties(FileServerProperties.class)
@Configuration
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = "LOCAL", matchIfMissing = true)
@Slf4j
public class LocalAutoConfigure {

    @Bean
    public FileStrategy getFileStrategy(FileServerProperties fileProperties) {
        return new LocalFileStrategyImpl(fileProperties);
    }

    @Bean
    public FileChunkStrategy getFileChunkStrategy(AttachmentService fileService, FileServerProperties fileProperties) {
        return new LocalFileChunkStrategyImpl(fileService, fileProperties);
    }

}
