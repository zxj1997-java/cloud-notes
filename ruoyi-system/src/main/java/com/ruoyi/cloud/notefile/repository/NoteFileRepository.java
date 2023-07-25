package com.ruoyi.cloud.notefile.repository;

import com.ruoyi.cloud.notefile.entity.NoteFile;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteFileRepository extends MongoRepository<NoteFile, String> {
    List<NoteFile> findAllBy(TextCriteria criteria);

    List<NoteFile> findByTitleContainingIgnoreCase(String keyword);

    List<NoteFile> findByContentContainingIgnoreCase(String keyword);

    List<NoteFile> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
}