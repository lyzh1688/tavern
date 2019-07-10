package com.tuoyou.tavern.alleria.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.alleria.dto.FileUploadDTO;
import com.tuoyou.tavern.protocol.alleria.model.FileUploadRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileUploadRecordMapper extends BaseMapper<FileUploadRecord> {
    IPage<List<FileUploadRecord>> selectFileUploadRecordPage(Page page, @Param("query") FileUploadDTO fileUploadDTO);
}