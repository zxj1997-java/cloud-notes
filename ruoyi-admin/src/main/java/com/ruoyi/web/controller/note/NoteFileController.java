package com.ruoyi.web.controller.note;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.note.file.entity.FileEntity;
import com.ruoyi.note.file.entity.NoteFile;
import com.ruoyi.note.file.repository.NoteRepository;
import com.ruoyi.note.file.service.MarkdownService;
import com.ruoyi.note.file.service.MongoDbUploaderService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/notefile")
public class NoteFileController {
    @Autowired
    private NoteRepository repository;
    @Autowired
    private MarkdownService markdownService;

    @Autowired
    private MongoDbUploaderService uploaderService;

    @PostMapping
    public NoteFile save(@RequestBody NoteFile note) {
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
        return noteFile;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        repository.deleteById(id);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("files") MultipartFile[] files) {
        try {
            MultipartFile file = files[0];
            String fileName = Paths.get(file.getOriginalFilename()).getFileName().toString();
            String contentType = file.getContentType();
            InputStream inputStream = file.getInputStream();
            FileEntity fileEntity = uploaderService.upload(inputStream, fileName, contentType);
            return fileEntity.getId();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/image/{id}")
    public void getImage(@PathVariable String id, HttpServletResponse response) throws IOException {
        FileEntity optionalFile = uploaderService.download(id);
        if (optionalFile != null) {
            response.setContentType(optionalFile.getContentType());
            InputStream inputStream = new ByteArrayInputStream(optionalFile.getContent());
            IOUtils.copy(inputStream, response.getOutputStream());
        }
    }
}