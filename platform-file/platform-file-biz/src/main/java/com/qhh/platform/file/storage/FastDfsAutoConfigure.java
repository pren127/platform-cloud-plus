package com.qhh.platform.file.storage;

import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qhh.platform.file.dao.AttachmentMapper;
import com.qhh.platform.file.properties.FileServerProperties;
import com.qhh.platform.file.service.AttachmentService;
import com.qhh.platform.file.strategy.FileChunkStrategy;
import com.qhh.platform.file.strategy.FileStrategy;
import com.qhh.platform.file.strategy.impl.fastdfs.FastDfsFileChunkStrategyImpl;
import com.qhh.platform.file.strategy.impl.fastdfs.FastDfsFileStrategyImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FastDFS配置
 *
 * @author qhh
 */
@EnableConfigurationProperties(FileServerProperties.class)
@Configuration
@Slf4j
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = "FAST_DFS")
public class FastDfsAutoConfigure {
    @Bean
    public FileStrategy getFileStrategy(FileServerProperties fileProperties, FastFileStorageClient storageClient, AttachmentMapper attachmentMapper) {
        return new FastDfsFileStrategyImpl(fileProperties, storageClient, attachmentMapper);
    }

    @Bean
    public FileChunkStrategy getFileChunkStrategy(AttachmentService fileService, FileServerProperties fileProperties, AppendFileStorageClient storageClient) {
        return new FastDfsFileChunkStrategyImpl(fileService, fileProperties, storageClient);
    }
}
