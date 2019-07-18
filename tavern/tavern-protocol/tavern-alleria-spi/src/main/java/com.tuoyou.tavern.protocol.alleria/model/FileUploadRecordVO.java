package com.tuoyou.tavern.protocol.alleria.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FileUploadRecordVO  implements Serializable {
    private String batchId;

    private String packageName;

    private Integer fileCount;

    private String uploadDate;

    private String status;

    private String operator;

    private String updateDate;

    private String isValid;

    private String packageType;
}