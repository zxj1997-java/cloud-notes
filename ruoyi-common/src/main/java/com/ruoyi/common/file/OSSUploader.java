package com.ruoyi.common.file;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class OSSUploader {
    @Autowired
    private OSS ossClient;

    public void upload(File file, String bucketName, String objectName) {
        PutObjectRequest request = new PutObjectRequest(bucketName, objectName, file);
        PutObjectResult result = ossClient.putObject(request);
        System.out.println(result.getETag());
    }
}