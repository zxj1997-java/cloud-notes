package com.ruoyi.web.controller.note;

import cn.hutool.core.io.FileUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.note.domain.Note;
import com.ruoyi.note.file.entity.NoteFile;
import com.ruoyi.note.file.repository.NoteRepository;
import com.ruoyi.note.file.service.MarkdownService;
import com.ruoyi.note.service.INoteService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2023-07-20
 */
@RestController
@RequestMapping("/note")
public class NoteController extends BaseController {
    @Autowired
    private INoteService noteService;
    @Autowired
    private NoteRepository repository;
    @Autowired
    private MarkdownService markdownService;

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/children")
    public TableDataInfo children(Note note) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        note.setUserId(user.getUserId());
        List<Note> list = noteService.selectNoteList(note);
        return getDataTable(list);
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/parents")
    public TableDataInfo parents(Note note) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        note.setUserId(user.getUserId());
        String parent = noteService.findParentId(note.getId());
        note.setParentId(parent);
        note.setId(null);
        List<Note> list = noteService.selectNoteList(note);
        return getDataTable(list);
    }


    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(noteService.selectNoteById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Note note) {
        Date date = new Date();
        SysUser user = SecurityUtils.getLoginUser().getUser();
        String userId = String.valueOf(user.getUserId());

        note.setId(UUID.randomUUID().toString());
        note.setIsDeleted(0L);
        note.setCreateTime(date);
        note.setUpdateTime(date);
        note.setCreateBy(userId);
        note.setUpdateBy(userId);
        note.setUserId(user.getUserId());
        noteService.insertNote(note);
        return success(note);
    }

    /**
     * 修改【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Note note) {
        noteService.updateNote(note);
        NoteFile noteFile = repository.findById(note.getId()).orElse(new NoteFile());
        noteFile.setId(note.getId());
        noteFile.setTitle(note.getFilename());
        repository.save(noteFile);
        return toAjax(true);
    }

    /**
     * 删除【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        List<String> list = new ArrayList<>(Arrays.asList(ids));
        findChildren(list);
        return toAjax(noteService.deleteNoteByIds(list));
    }

    public List<String> findChildren(List<String> ids) {
        List<Note> children = noteService.findChildren(ids);
        if (CollectionUtils.isEmpty(children)) {
            return ids;
        } else {
            List<String> childIds = children.stream().map(Note::getId).collect(Collectors.toList());
            ids.addAll(childIds);
            return findChildren(childIds); // 添加 return 语句
        }
    }

    @PostMapping("/uploadFile")
    public AjaxResult handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("parentId") String parentId) throws IOException {
        SysUser user = SecurityUtils.getLoginUser().getUser();

        File tempDir = new File(System.getProperty("java.io.tmpdir"), "uploaded-folder");
        FileUtils.deleteDirectory(tempDir);
        tempDir.mkdirs();

        file.transferTo(new File(tempDir, file.getOriginalFilename()));

        parseFolder(tempDir, user.getUserId(), parentId);
        return AjaxResult.success();
    }

    private void parseFolder(File folder, long userId, String parentId) throws IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                Note note = new Note(UUID.randomUUID().toString(), file.getName(), 1L, parentId, userId);
                noteService.insertNote(note);
                //文件夹
                parseFolder(file, userId, note.getId());
            } else {
                //文件
                Note note = new Note(UUID.randomUUID().toString(), file.getName(), 0L, parentId, userId);
                noteService.insertNote(note);

                NoteFile noteFile = new NoteFile();
                noteFile.setId(note.getId());
                noteFile.setTitle(note.getFilename());
                noteFile.setContent(FileUtil.readString(file, Charset.defaultCharset()));
                markdownService.save(noteFile);
            }
        }
    }


    /**
     * 导出【请填写功能名称】列表
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Note note) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        note.setUserId(user.getUserId());
        List<Note> list = noteService.selectNoteList(note);
        ExcelUtil<Note> util = new ExcelUtil<Note>(Note.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }
}
