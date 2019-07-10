package com.tuoyou.tavern.alleria.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.protocol.alleria.dto.FileUploadDTO;
import com.tuoyou.tavern.protocol.alleria.model.FileUploadRecord;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
public interface FileUploadRecordService extends IService<FileUploadRecord> {
    IPage<List<FileUploadRecord>> getRecordWithTypeAndStatusByPage(Page page, FileUploadDTO fileUploadDTO);
}
