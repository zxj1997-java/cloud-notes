package com.ruoyi.cloud.note.service;

import com.ruoyi.cloud.note.domain.Note;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2023-07-20
 */
public interface INoteService {
    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Note selectNoteById(String id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param note 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Note> selectNoteList(Note note);

    /**
     * 新增【请填写功能名称】
     *
     * @param note 【请填写功能名称】
     * @return 结果
     */
    public int insertNote(Note note);

    /**
     * 修改【请填写功能名称】
     *
     * @param note 【请填写功能名称】
     * @return 结果
     */
    public int updateNote(Note note);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteNoteByIds(List<String> ids);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteNoteById(String id);

    /**
     * 查询父节点id
     *
     * @param id
     * @return
     */
    String findParentId(String id);

    /**
     * 查询子节点
     *
     * @param ids
     * @return
     */
    List<Note> findChildren(List<String> ids);
}
