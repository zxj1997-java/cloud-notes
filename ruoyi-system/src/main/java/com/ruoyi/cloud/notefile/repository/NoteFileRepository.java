package com.ruoyi.cloud.notefile.repository;

import com.ruoyi.cloud.notefile.entity.NoteFile;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteFileRepository extends MongoRepository<NoteFile, String> {
    List<NoteFile> findAllBy(TextCriteria criteria);

    @Query("{ $text: { $search: ?0 } }")
    List<NoteFile> findByText(String text);
}