package com.qhh.platform.file.storage;

import com.qhh.platform.file.properties.FileServerProperties;
import com.qhh.platform.file.service.AttachmentService;
import com.qhh.platform.file.strategy.FileChunkStrategy;
import com.qhh.platform.file.strategy.FileStrategy;
import com.qhh.platform.file.strategy.impl.ali.AliFileChunkStrategyImpl;
import com.qhh.platform.file.strategy.impl.ali.AliFileStrategyImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里OSS
 *
 * @author qhh
 * @date 2019/08/09
 */
@EnableConfigurationProperties(FileServerProperties.class)
@Configuration
@Slf4j
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = "ALI")
public class AliOssAutoConfigure {

    @Bean
    public FileStrategy getFileStrategy(FileServerProperties fileProperties) {
        return new AliFileStrategyImpl(fileProperties);
    }

    @Bean
    public FileChunkStrategy getFileChunkStrategy(AttachmentService fileService, FileServerProperties fileProperties) {
        return new AliFileChunkStrategyImpl(fileService, fileProperties);
    }
}
