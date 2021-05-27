package com.qhh.platform.file.strategy.impl.fastdfs;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qhh.platform.file.dao.AttachmentMapper;
import com.qhh.platform.file.domain.FileDeleteDO;
import com.qhh.platform.file.entity.Attachment;
import com.qhh.platform.file.properties.FileServerProperties;
import com.qhh.platform.file.strategy.impl.AbstractFileStrategy;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qhh
 * @date 2020/11/22 5:17 下午
 */
@DS("#thread.tenant")
public class FastDfsFileStrategyImpl extends AbstractFileStrategy {
    private final FastFileStorageClient storageClient;
    private final AttachmentMapper attachmentMapper;

    public FastDfsFileStrategyImpl(FileServerProperties fileProperties, FastFileStorageClient storageClient, AttachmentMapper attachmentMapper) {
        super(fileProperties);
        this.storageClient = storageClient;
        this.attachmentMapper = attachmentMapper;
    }

    @Override
    protected void uploadFile(Attachment file, MultipartFile multipartFile) throws Exception {
        StorePath storePath = storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), file.getExt(), null);
        file.setUrl(fileProperties.getUriPrefix() + storePath.getFullPath());
        file.setGroup(storePath.getGroup());
        file.setPath(storePath.getPath());
    }

    @Override
    protected void delete(List<FileDeleteDO> list, FileDeleteDO file) {
        if (file.getFile()) {
            List<Long> ids = list.stream().mapToLong(FileDeleteDO::getId).boxed().collect(Collectors.toList());
            Integer count = attachmentMapper.countByGroup(ids, file.getGroup(), file.getPath());
            if (count > 0) {
                return;
            }
        }
        storageClient.deleteFile(file.getGroup(), file.getPath());
    }

    @Override
    public List<String> getUrls(List<String> paths, Integer expiry) {
        return null;
    }

    @Override
    public String getUrl(String path, Integer expiry) {
        return null;
    }
}

