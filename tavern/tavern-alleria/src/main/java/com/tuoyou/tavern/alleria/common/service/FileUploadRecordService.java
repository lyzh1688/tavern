package com.tuoyou.tavern.alleria.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tuoyou.tavern.alleria.util.FileTransfer;
import com.tuoyou.tavern.protocol.alleria.dto.FileUploadDTO;
import com.tuoyou.tavern.protocol.alleria.model.FileUploadRecord;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/03 <br>
 */
public interface FileUploadRecordService extends IService<FileUploadRecord> {
    IPage<FileUploadRecord> getRecordWithTypeAndStatusByPage(Page page, FileUploadDTO fileUploadDTO);

    void uploadFile(MultipartFile multipartFile,
                    String destLocation, String type,
                    String batchId,
                    BiConsumer<FileTransfer,HttpSession> biConsumer,
                    HttpSession session) throws Exception;
}
