package com.ruoyi.cloud.note.service.impl;

import com.ruoyi.cloud.note.domain.Note;
import com.ruoyi.cloud.note.mapper.NoteMapper;
import com.ruoyi.cloud.note.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2023-07-20
 */
@Service
public class NoteServiceImpl implements INoteService {
    @Autowired
    private NoteMapper noteMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Note selectNoteById(String id) {
        return noteMapper.selectNoteById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param note 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Note> selectNoteList(Note note) {
        return noteMapper.selectNoteList(note);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param note 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNote(Note note) {
        return noteMapper.insertNote(note);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param note 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNote(Note note) {
        return noteMapper.updateNote(note);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNoteByIds(List<String> ids) {
        return noteMapper.deleteNoteByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNoteById(String id) {
        return noteMapper.deleteNoteById(id);
    }

    /**
     * 查询父节点id
     *
     * @param id
     * @return
     */
    @Override
    public String findParentId(String id) {
        return noteMapper.findParentId(id);
    }

    /**
     * 查询子节点
     *
     * @param ids
     * @return
     */
    @Override
    public List<Note> findChildren(List<String> ids) {
        return noteMapper.findChildren(ids);
    }
}
