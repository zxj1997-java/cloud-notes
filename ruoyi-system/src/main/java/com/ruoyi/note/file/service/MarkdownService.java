package com.ruoyi.note.file.service;

import com.ruoyi.note.file.entity.NoteFile;
import com.ruoyi.note.file.repository.NoteRepository;
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