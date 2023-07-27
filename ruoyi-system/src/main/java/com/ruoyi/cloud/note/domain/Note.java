package com.ruoyi.cloud.note.domain;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

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

    public Date getShareTime() {
        return shareTime;
    }

    public void setShareTime(Date shareTime) {
        this.shareTime = shareTime;
    }

    public Long getShareHours() {
        return shareHours;
    }

    public void setShareHours(Long shareHours) {
        this.shareHours = shareHours;
    }

    public Note() {
    }

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

    public Long getIsShare() {
        return isShare;
    }

    public void setIsShare(Long isShare) {
        this.isShare = isShare;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", filename='" + filename + '\'' +
                ", isDirectory=" + isDirectory +
                ", parentId='" + parentId + '\'' +
                ", isShare=" + isShare +
                ", isTop=" + isTop +
                ", isFavorite=" + isFavorite +
                ", isDeleted=" + isDeleted +
                ", userId=" + userId +
                ", shareTime=" + shareTime +
                ", shareHours=" + shareHours +
                '}';
    }
}
