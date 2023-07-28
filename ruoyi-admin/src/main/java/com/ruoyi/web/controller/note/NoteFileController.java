package com.ruoyi.web.controller.note;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.cloud.note.domain.Note;
import com.ruoyi.cloud.note.service.INoteService;
import com.ruoyi.cloud.notefile.entity.FileEntity;
import com.ruoyi.cloud.notefile.entity.NoteFile;
import com.ruoyi.cloud.notefile.repository.NoteFileRepository;
import com.ruoyi.cloud.notefile.service.MongoDbUploaderService;
import com.ruoyi.cloud.notefile.service.NoteFileService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/notefile")
public class NoteFileController extends BaseController {
    @Autowired
    private NoteFileRepository repository;
    @Autowired
    private NoteFileService noteFileService;

    @Autowired
    private MongoDbUploaderService uploaderService;

    @Autowired
    private INoteService noteService;

    @PostMapping
    public NoteFile save(@RequestBody NoteFile noteFile) {
        Note note = noteService.selectNoteById(noteFile.getId());
        note.setUpdateTime(new Date());
        noteService.updateNote(note);
        if (StringUtils.isEmpty(noteFile.getContent())) {
            noteFile.setContent("");
            noteFile.setUserId(getUserId());
            noteFile.setIsDeleted(0);
        }
        return noteFileService.save(noteFile);
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
    public String uploadFile(@RequestParam("files") MultipartFile[] files) throws IOException {
        MultipartFile file = files[0];
        String fileName = Paths.get(file.getOriginalFilename()).getFileName().toString();
        String contentType = file.getContentType();
        InputStream inputStream = file.getInputStream();
        FileEntity fileEntity = uploaderService.upload(inputStream, fileName, contentType);
        return fileEntity.getId();
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


    @PostMapping("/search")
    public List<NoteFile> search(@RequestBody String keyword) {
        List<NoteFile> list = noteFileService.search(keyword, getUserId());
        for (NoteFile noteFile : list) {
            String content = noteFile.getContent();
            if(StrUtil.isNotBlank(content)){
                String replaceStr = "<span style=\"color:#ff0000\">" + keyword + "</span>";
                noteFile.setContent(content.replaceAll(keyword, replaceStr));
            }
        }
        return list;
    }

    @GetMapping("/searchByTitle")
    public List<NoteFile> searchByTitle(String keyword) {
        return noteFileService.searchByTitle(keyword);
    }
}