package com.tuoyou.tavern.protocol.alleria.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ALR_FILE_UPLOAD_RECORD")
public class FileUploadRecord extends Model<FileUploadRecord> implements Serializable {
    @TableId
    private String batchId;

    private String packageName;

    private Integer fileCount;

    private LocalDateTime uploadDate;

    private String status;

    private String operator;

    private LocalDateTime updateDate;

    private String isValid;

    private String packageType;
}