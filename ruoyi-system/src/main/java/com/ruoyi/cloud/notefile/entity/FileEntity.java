package com.ruoyi.cloud.notefile.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "file_entity")
public class FileEntity {
    @Id
    private String id;

    private String name;

    private String contentType;

    private byte[] content;
}