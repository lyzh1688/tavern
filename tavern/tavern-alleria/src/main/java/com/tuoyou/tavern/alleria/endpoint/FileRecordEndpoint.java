package com.tuoyou.tavern.alleria.endpoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.alleria.dto.FileUploadDTO;
import com.tuoyou.tavern.alleria.common.service.FileUploadRecordService;
import com.tuoyou.tavern.protocol.alleria.model.FileUploadProcess;
import com.tuoyou.tavern.protocol.alleria.response.FileUploadProcessResponse;
import com.tuoyou.tavern.protocol.alleria.response.FileUploadRecordResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/file")
public class FileRecordEndpoint {

    private final FileUploadRecordService fileUploadRecordService;

    /*
     * 文件上传列表查询
     * */
    //RESOURCE-PATH:/packages/{fileType}?page={page}&size={size}&status={status}
    @GetMapping(value = "/page")
    public FileUploadRecordResponse queryPackages(Page page, FileUploadDTO fileUploadDTO) {
        return new FileUploadRecordResponse(fileUploadRecordService.getRecordWithTypeAndStatusByPage(page, fileUploadDTO));

    }


    /*
     * 查询文件上传进度
     * */
    @GetMapping(value = "/percentage")
    public FileUploadProcessResponse queryInvoiceUploadPercentage(@RequestParam("batchId") String batchId, HttpSession httpSession) {
        return new FileUploadProcessResponse(FileUploadProcess.builder().percentage((String) httpSession.getAttribute(batchId)).build());
    }

}
