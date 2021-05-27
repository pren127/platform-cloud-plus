package com.qhh.platform.file.strategy.impl.local;

import cn.hutool.core.util.StrUtil;
import com.qhh.basic.context.ContextUtil;
import com.qhh.basic.utils.StrPool;
import com.qhh.platform.file.domain.FileDeleteDO;
import com.qhh.platform.file.entity.Attachment;
import com.qhh.platform.file.properties.FileServerProperties;
import com.qhh.platform.file.strategy.impl.AbstractFileStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static com.qhh.basic.utils.DateUtils.DEFAULT_MONTH_FORMAT_SLASH;

/**
 * @author qhh
 * @date 2020/11/22 5:00 下午
 */
@Slf4j
public class LocalFileStrategyImpl extends AbstractFileStrategy {
    public LocalFileStrategyImpl(FileServerProperties fileProperties) {
        super(fileProperties);
    }

    @Override
    protected void uploadFile(Attachment file, MultipartFile multipartFile) throws Exception {
        //生成文件名
        String fileName = UUID.randomUUID().toString() + StrPool.DOT + file.getExt();

        String tenant = ContextUtil.getTenant();

        //日期文件夹
        String relativePath = Paths.get(tenant, LocalDate.now().format(DateTimeFormatter.ofPattern(DEFAULT_MONTH_FORMAT_SLASH))).toString();
        // web服务器存放的绝对路径
        String absolutePath = Paths.get(fileProperties.getStoragePath(), relativePath).toString();

        java.io.File outFile = new java.io.File(Paths.get(absolutePath, fileName).toString());
        org.apache.commons.io.FileUtils.writeByteArrayToFile(outFile, multipartFile.getBytes());

        String url = fileProperties.getUriPrefix() +
                relativePath +
                StrPool.SLASH +
                fileName;
        //替换掉windows环境的\路径
        url = StrUtil.replace(url, "\\\\", StrPool.SLASH);
        url = StrUtil.replace(url, "\\", StrPool.SLASH);
        file.setUrl(url);
        file.setFilename(fileName);
        file.setRelativePath(relativePath);
    }

    @Override
    protected void delete(List<FileDeleteDO> list, FileDeleteDO file) {
        java.io.File ioFile = new java.io.File(Paths.get(fileProperties.getStoragePath(), file.getRelativePath(), file.getFileName()).toString());
        org.apache.commons.io.FileUtils.deleteQuietly(ioFile);
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
