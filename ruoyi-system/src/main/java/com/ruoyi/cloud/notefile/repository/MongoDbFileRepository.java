package com.ruoyi.cloud.notefile.repository;

import com.ruoyi.cloud.notefile.entity.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDbFileRepository extends MongoRepository<FileEntity, String> {
}