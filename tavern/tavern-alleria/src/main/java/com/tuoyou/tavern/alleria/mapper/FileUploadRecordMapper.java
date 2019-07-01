package com.tuoyou.tavern.alleria.mapper;

import com.tuoyou.tavern.alleria.domain.FileUploadRecord;
import java.util.List;

public interface FileUploadRecordMapper {
    int deleteByPrimaryKey(String batchId);

    int insert(FileUploadRecord record);

    FileUploadRecord selectByPrimaryKey(String batchId);

    List<FileUploadRecord> selectAll();

    int updateByPrimaryKey(FileUploadRecord record);
}