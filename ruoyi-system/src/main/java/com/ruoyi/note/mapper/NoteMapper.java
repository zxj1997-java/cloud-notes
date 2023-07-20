package com.ruoyi.note.mapper;


import com.ruoyi.note.domain.Note;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-20
 */
@Mapper
public interface NoteMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Note selectNoteById(Long id);

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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteNoteById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNoteByIds(List<Long> ids);
    /**
     * 查询父节点id
     * @param id
     * @return
     */
    Long findParentId(Long id);
    /**
     * 查询子节点
     *
     * @param ids
     * @return
     */
    List<Note> findChildren(List<Long> ids);
}
