package com.ruoyi.cloud.notefile.repository;

import com.ruoyi.cloud.notefile.entity.NoteFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteFileRepository extends MongoRepository<NoteFile, String> {
}