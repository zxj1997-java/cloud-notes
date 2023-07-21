package com.ruoyi.note.file.repository;

import com.ruoyi.note.file.entity.NoteFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<NoteFile, String> {
}