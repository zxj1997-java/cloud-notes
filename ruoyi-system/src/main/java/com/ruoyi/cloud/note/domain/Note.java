package com.ruoyi.cloud.note.domain;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 【请填写功能名称】对象 note
 *
 * @author ruoyi
 * @date 2023-07-20
 */
@Data
@NoArgsConstructor
public class Note extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private String id;
    /**
     *
     */
    @Excel(name = "")
    private String filename;
    /**
     *
     */
    @Excel(name = "")
    private Long isDirectory;
    /**
     *
     */
    @Excel(name = "")
    private String parentId;
    /**
     *
     */
    @Excel(name = "")
    private Long isShare;
    /**
     *
     */
    @Excel(name = "")
    private Long isTop;
    /**
     *
     */
    @Excel(name = "")
    private Long isFavorite;
    /**
     *
     */
    @Excel(name = "")
    private Long isDeleted;
    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;


    /**
     * 分享时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shareTime;


    /**
     * 分享时间
     */
    private Long shareHours;

    public Note(String id, String filename, Long isDirectory, String parentId, Long userId) {
        this.id = id;
        this.filename = filename;
        this.isDirectory = isDirectory;
        if(StrUtil.isNotBlank(parentId)){
            this.parentId = parentId;
        }
        this.isDeleted = 0L;
        this.userId = userId;
        setCreateBy(String.valueOf(userId));
        Date date = new Date();
        setCreateTime(date);
        setCreateTime(date);
        setUpdateBy(String.valueOf(userId));
    }
}
