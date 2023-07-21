package com.ruoyi.note.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 note
 *
 * @author ruoyi
 * @date 2023-07-20
 */
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getIsDirectory() {
        return isDirectory;
    }

    public void setIsDirectory(Long isDirectory) {
        this.isDirectory = isDirectory;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getIsTop() {
        return isTop;
    }

    public void setIsTop(Long isTop) {
        this.isTop = isTop;
    }

    public Long getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Long isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
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
