package com.tuoyou.tavern.alleria.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tuoyou.tavern.alleria.common.dao.FileUploadRecordMapper;
import com.tuoyou.tavern.alleria.configuration.TTLContext;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.common.core.util.DateUtils;
import com.tuoyou.tavern.common.core.util.UUIDUtil;
import com.tuoyou.tavern.invoice.common.libs.utils.FileUtils;
import com.tuoyou.tavern.protocol.alleria.common.FileUploadStatus;
import com.tuoyou.tavern.protocol.alleria.dto.FileUploadDTO;
import com.tuoyou.tavern.alleria.common.service.FileUploadRecordService;
import com.tuoyou.tavern.protocol.alleria.model.FileUploadRecord;
import com.tuoyou.tavern.protocol.alleria.model.FileUploadRecordVO;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
@Service
@AllArgsConstructor
public class FileUploadRecordServiceImpl extends ServiceImpl<FileUploadRecordMapper, FileUploadRecord> implements FileUploadRecordService {

    private final TTLContext ttlContext;

    @Override
    public IPage<FileUploadRecordVO> getRecordWithTypeAndStatusByPage(Page page, FileUploadDTO fileUploadDTO) {
        IPage<FileUploadRecord> fileUploadRecordIPage = this.baseMapper.selectFileUploadRecordPage(page, fileUploadDTO);
        List<FileUploadRecordVO> fileUploadRecordVOList = fileUploadRecordIPage.getRecords()
                .stream()
                .map(record -> {
                    FileUploadRecordVO fileUploadRecordVO = new FileUploadRecordVO();
                    BeanUtils.copyProperties(record, fileUploadRecordVO);
                    fileUploadRecordVO.setUpdateDate(DateUtils.formatDateTime(record.getUpdateDate(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    fileUploadRecordVO.setUploadDate(DateUtils.formatDateTime(record.getUploadDate(), DateUtils.DEFAULT_DATETIME_FORMATTER));
                    return fileUploadRecordVO;
                }).collect(Collectors.toList());
        Page<FileUploadRecordVO> uploadRecordVOPage = new Page<>();
        uploadRecordVOPage.setRecords(fileUploadRecordVOList);
        uploadRecordVOPage.setCurrent(fileUploadRecordIPage.getCurrent());
        uploadRecordVOPage.setSize(fileUploadRecordIPage.getSize());
        uploadRecordVOPage.setCurrent(fileUploadRecordIPage.getCurrent());
        uploadRecordVOPage.setTotal(fileUploadRecordIPage.getTotal());
        return uploadRecordVOPage;
    }

    @Override
    public void uploadFile(MultipartFile multipartFile, String destLocation, String type, String batchId,
                           Consumer<FileTransfer> consumer) throws Exception {
        //1. 文件包落地
        //2. 文件包解压
        //3. 文件解析
        FileUploadRecord fileUploadRecord = null;
        JSONObject fileUploadObject = new JSONObject();
        try {
            fileUploadObject.put("percentage",1);
            fileUploadObject.put("status",FileUploadStatus.IN_PROCESS);
            ttlContext.putValue(batchId, fileUploadObject);
            String filePath = FileUtils.multiPartFileWriter(multipartFile, destLocation);
            fileUploadObject.put("percentage",10);
            ttlContext.putValue(batchId, fileUploadObject);
            String destFileDir = FileUtils.unZip(new File(filePath), destLocation);
            fileUploadObject.put("percentage",20);
            ttlContext.putValue(batchId, fileUploadObject);
            File file = new File(destFileDir);
            int cnt = file.listFiles().length;
            fileUploadRecord = new FileUploadRecord();
            fileUploadRecord.setBatchId(batchId);
            fileUploadRecord.setPackageName(multipartFile.getOriginalFilename());
            fileUploadRecord.setFileCount(cnt);
            fileUploadRecord.setUploadDate(LocalDateTime.now());
            fileUploadRecord.setStatus("1");
            fileUploadRecord.setOperator("admin");
            fileUploadRecord.setUpdateDate(LocalDateTime.now());
            fileUploadRecord.setIsValid("1");
            fileUploadRecord.setPackageType(type);
            this.baseMapper.insert(fileUploadRecord);
            FileTransfer fileTransfer = FileTransfer.builder()
                    .batchId(batchId)
                    .destLocation(destFileDir)
                    .build();
            consumer.accept(fileTransfer);
            fileUploadRecord.setStatus("2");
            this.baseMapper.updateById(fileUploadRecord);
            fileUploadObject.put("status", FileUploadStatus.FINISHED);
            ttlContext.putValue(batchId, fileUploadObject);
        } catch (Exception e) {
            if (fileUploadRecord != null) {
                fileUploadRecord.setStatus("3");
                this.baseMapper.updateById(fileUploadRecord);
            }
            fileUploadObject.put("status", FileUploadStatus.FAILED);
            ttlContext.putValue(batchId, fileUploadObject);
            throw new Exception("文件上传失败");
        }


    }
}
