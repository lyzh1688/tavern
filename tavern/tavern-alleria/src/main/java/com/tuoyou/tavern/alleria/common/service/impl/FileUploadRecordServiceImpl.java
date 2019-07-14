package com.tuoyou.tavern.alleria.common.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.common.dao.FileUploadRecordMapper;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.invoice.common.libs.utils.FileUtils;
import com.tuoyou.tavern.protocol.alleria.dto.FileUploadDTO;
import com.tuoyou.tavern.alleria.common.service.FileUploadRecordService;
import com.tuoyou.tavern.protocol.alleria.model.FileUploadRecord;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
@Service
@AllArgsConstructor
public class FileUploadRecordServiceImpl extends ServiceImpl<FileUploadRecordMapper, FileUploadRecord> implements FileUploadRecordService {

    @Override
    public IPage<FileUploadRecord> getRecordWithTypeAndStatusByPage(Page page, FileUploadDTO fileUploadDTO) {
        return this.baseMapper.selectFileUploadRecordPage(page, fileUploadDTO);
    }

    @Override
    public void uploadFile(MultipartFile multipartFile, String destLocation, String type, String batchId,
                           BiConsumer<FileTransfer, HttpSession> biConsumer,
                           HttpSession httpSession) throws Exception {
        //1. 文件包落地
        //2. 文件包解压
        //3. 文件解析
        FileUploadRecord fileUploadRecord = null;
        try {
            httpSession.setAttribute(batchId, 1);
            String filePath = FileUtils.multiPartFileWriter(multipartFile, destLocation);
            httpSession.setAttribute(batchId, 10);
            String destFileDir = FileUtils.unZip(new File(filePath), destLocation);
            httpSession.setAttribute(batchId, 20);
            File file = new File(destFileDir);
            int cnt = file.listFiles().length;
            fileUploadRecord = FileUploadRecord
                    .builder()
                    .batchId(batchId)
                    .packageName(multipartFile.getOriginalFilename())
                    .fileCount(cnt)
                    .uploadDate(LocalDateTime.now())
                    .status("1")
                    .operator("admin")
                    .uploadDate(LocalDateTime.now())
                    .isValid("1")
                    .packageType(type)
                    .build();
            this.baseMapper.updateById(fileUploadRecord);
            FileTransfer fileTransfer = FileTransfer.builder()
                    .batchId(batchId)
                    .destLocation(destFileDir)
                    .build();
            biConsumer.accept(fileTransfer, httpSession);
            fileUploadRecord.setStatus("2");
            this.baseMapper.updateById(fileUploadRecord);
        } catch (Exception e) {
            if (fileUploadRecord != null) {
                fileUploadRecord.setStatus("3");
                this.baseMapper.updateById(fileUploadRecord);
            }
            throw new Exception("文件上传失败");
        }


    }
}
