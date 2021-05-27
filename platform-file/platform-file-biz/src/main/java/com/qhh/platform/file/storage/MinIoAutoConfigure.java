package com.qhh.platform.file.storage;

import com.qhh.platform.file.properties.FileServerProperties;
import com.qhh.platform.file.service.AttachmentService;
import com.qhh.platform.file.strategy.FileChunkStrategy;
import com.qhh.platform.file.strategy.FileStrategy;
import com.qhh.platform.file.strategy.impl.minio.MinIoFileChunkStrategyImpl;
import com.qhh.platform.file.strategy.impl.minio.MinIoFileStrategyImpl;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = "MIN_IO", matchIfMissing = true)
@Slf4j
public class MinIoAutoConfigure {

    /**
     * 初始化minio客户端,不用每次都初始化
     *
     * @return MinioClient
     * @author qhh
     */
    @Bean
    public MinioClient minioClient(FileServerProperties properties) {
        return new MinioClient.Builder()
                .endpoint(properties.getMinIo().getEndpoint())
                .credentials(properties.getMinIo().getAccessKey(), properties.getMinIo().getSecretKey())
                .build();
    }

    @Bean
    public FileStrategy getFileStrategy(FileServerProperties properties, MinioClient minioClient) {
        return new MinIoFileStrategyImpl(properties, minioClient);
    }

    @Bean
    public FileChunkStrategy getFileChunkStrategy(AttachmentService fileService, FileServerProperties properties) {
        return new MinIoFileChunkStrategyImpl(fileService, properties);
    }
}
