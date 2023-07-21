package com.ruoyi.note.file.service;

import com.ruoyi.note.file.entity.FileEntity;
import com.ruoyi.note.file.repository.FileRepository;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class MongoDbUploaderService {
    @Autowired
    private FileRepository fileRepository;

    public void upload(InputStream inputStream, String fileName, String contentType) throws IOException {
        byte[] content = IOUtils.toByteArray(inputStream);
        FileEntity file = new FileEntity();
        file.setName(fileName);
        file.setContentType(contentType);
        file.setContent(content);
        fileRepository.save(file);
    }
}