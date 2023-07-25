package com.ruoyi.cloud.notefile.service;

import com.ruoyi.cloud.notefile.entity.FileEntity;
import com.ruoyi.cloud.notefile.repository.MongoDbFileRepository;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Component
public class MongoDbUploaderService {
    @Autowired
    private MongoDbFileRepository fileRepository;

    public FileEntity upload(InputStream inputStream, String fileName, String contentType) throws IOException {
        byte[] content = IOUtils.toByteArray(inputStream);
        FileEntity file = new FileEntity();
        file.setName(fileName);
        file.setContentType(contentType);
        file.setContent(content);
        return fileRepository.save(file);
    }

    public FileEntity download(String fileId) throws IOException {
        Optional<FileEntity> optionalFile = fileRepository.findById(fileId);
        if (optionalFile.isPresent()) {
            return optionalFile.get();
        }
        return null;
    }
}