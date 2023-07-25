package com.ruoyi.cloud.notefile.service;

import com.ruoyi.cloud.notefile.entity.NoteFile;
import com.ruoyi.cloud.notefile.repository.NoteFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoteFileService {

    @Autowired
    private NoteFileRepository repository;


    public NoteFile save(NoteFile note) {
        Date date = new Date();
        if (note.getCreateTime() == null) {
            note.setCreateTime(date);
        }
        note.setUpdateTime(date);
        return repository.save(note);
    }

    public List<NoteFile> fullTextSearch(String text) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(text);
        List<NoteFile> results = repository.findAllBy(criteria);
        return results;
    }
}