package com.ruoyi.cloud.notefile.service;

import com.ruoyi.cloud.notefile.entity.NoteFile;
import com.ruoyi.cloud.notefile.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MarkdownService {

    @Autowired
    private NoteRepository repository;

    public NoteFile save(NoteFile note) {
        Date date = new Date();
        if (note.getCreateTime() == null) {
            note.setCreateTime(date);
        }
        note.setUpdateTime(date);
        return repository.save(note);
    }
}