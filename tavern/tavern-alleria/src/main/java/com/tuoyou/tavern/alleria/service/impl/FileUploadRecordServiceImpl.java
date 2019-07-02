package com.tuoyou.tavern.alleria.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.dao.FileUploadRecordMapper;
import com.tuoyou.tavern.alleria.dto.FileUploadDTO;
import com.tuoyou.tavern.alleria.entity.FileUploadRecord;
import com.tuoyou.tavern.alleria.service.FileUploadRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
@Service
@AllArgsConstructor
public class FileUploadRecordServiceImpl extends ServiceImpl<FileUploadRecordMapper, FileUploadRecord> implements FileUploadRecordService {

    @Override
    public IPage<List<FileUploadRecord>> getRecordWithTypeAndStatusByPage(Page page, FileUploadDTO fileUploadDTO) {
        return this.baseMapper.selectFileUploadRecordPage(page, fileUploadDTO);
    }
}
