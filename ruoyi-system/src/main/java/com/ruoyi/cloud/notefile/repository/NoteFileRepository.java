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

    List<NoteFile> findByTitleContainingIgnoreCase(String keyword);

    List<NoteFile> findByContentContainingIgnoreCase(String keyword);

    @Query("{ $and: [ { $or: [ { 'title': { $regex: ?0, $options: 'i'} }, { 'content': { $regex: ?1, $options: 'i' } } ] }, { 'isDeleted': ?2 }, { 'userId': ?3 } ] }")
    List<NoteFile> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseAndIsDeletedAndUserId(String title, String content, Integer isDeleted, Long userId);
}