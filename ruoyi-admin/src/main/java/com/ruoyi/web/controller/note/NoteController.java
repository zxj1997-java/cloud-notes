package com.ruoyi.web.controller.note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.mchange.v1.identicator.IdList;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.note.domain.Note;
import com.ruoyi.note.service.INoteService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;

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

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/children")
    public TableDataInfo children(Note note) {
        //SysUser user = SecurityUtils.getLoginUser().getUser();
        List<Note> list = noteService.selectNoteList(note);
        return getDataTable(list);
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @GetMapping("/parents")
    public TableDataInfo parents(Note note) {
        Long parent = noteService.findParentId(note.getId());
        note.setParentId(parent);
        note.setId(null);
        List<Note> list = noteService.selectNoteList(note);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Note note) {
        List<Note> list = noteService.selectNoteList(note);
        ExcelUtil<Note> util = new ExcelUtil<Note>(Note.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(noteService.selectNoteById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Note note) {
        return toAjax(noteService.insertNote(note));
    }

    /**
     * 修改【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Note note) {
        return toAjax(noteService.updateNote(note));
    }

    /**
     * 删除【请填写功能名称】
     */
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        List<Long> list = new ArrayList<>(Arrays.asList(ids));
        list = findChildren(list);
        return toAjax(noteService.deleteNoteByIds(list));
    }

    public List<Long> findChildren(List<Long> ids) {
        List<Note> children = noteService.findChildren(ids);
        if (CollectionUtils.isEmpty(children)) {
            return ids;
        } else {
            List<Long> childIds = children.stream().map(Note::getId).collect(Collectors.toList());
            ids.addAll(childIds);
            return findChildren(childIds); // 添加 return 语句
        }
    }
}
