package com.ruoyi.web.controller.note;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.note.file.entity.NoteFile;
import com.ruoyi.note.file.repository.NoteRepository;
import com.ruoyi.note.file.service.MarkdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/notefile")
public class NoteFileController {
    @Autowired
    private NoteRepository repository;
    @Autowired
    private MarkdownService markdownService;

    @PostMapping
    public NoteFile save(@RequestBody NoteFile note) {
        System.err.println(note);
        Date date = new Date();
        note.setCreateTime(date);
        note.setUpdateTime(date);
        if (StringUtils.isEmpty(note.getContent())) {
            note.setContent("");
        }
        return markdownService.save(note);
    }

    @GetMapping
    public List<NoteFile> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public NoteFile findById(@PathVariable String id) {
        NoteFile noteFile = repository.findById(id).orElse(null);
        System.err.println(noteFile);
        return noteFile;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        repository.deleteById(id);
    }
}