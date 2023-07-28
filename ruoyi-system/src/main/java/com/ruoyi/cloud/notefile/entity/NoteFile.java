package com.ruoyi.cloud.notefile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * MongoDB 笔记对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "note_file")
public class NoteFile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String title;

    private String content;
    private String html;
    private Date createTime;
    private Date updateTime;
    private Long userId;
    private Integer isDeleted;
}