package com.ruoyi.note.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 note
 * 
 * @author ruoyi
 * @date 2023-07-20
 */
public class Note extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    @Excel(name = "")
    private String filename;

    /**  */
    @Excel(name = "")
    private Long isDirectory;

    /**  */
    @Excel(name = "")
    private Long parentId;

    /**  */
    @Excel(name = "")
    private Long isTop;

    /**  */
    @Excel(name = "")
    private Long isFavorite;

    /**  */
    @Excel(name = "")
    private Long isDeleted;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFilename(String filename) 
    {
        this.filename = filename;
    }

    public String getFilename() 
    {
        return filename;
    }
    public void setIsDirectory(Long isDirectory) 
    {
        this.isDirectory = isDirectory;
    }

    public Long getIsDirectory() 
    {
        return isDirectory;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setIsTop(Long isTop) 
    {
        this.isTop = isTop;
    }

    public Long getIsTop() 
    {
        return isTop;
    }
    public void setIsFavorite(Long isFavorite) 
    {
        this.isFavorite = isFavorite;
    }

    public Long getIsFavorite() 
    {
        return isFavorite;
    }
    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("filename", getFilename())
            .append("isDirectory", getIsDirectory())
            .append("parentId", getParentId())
            .append("isTop", getIsTop())
            .append("isFavorite", getIsFavorite())
            .append("isDeleted", getIsDeleted())
            .append("userId", getUserId())
            .toString();
    }
}
