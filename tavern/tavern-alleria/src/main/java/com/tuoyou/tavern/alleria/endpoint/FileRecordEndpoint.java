package com.tuoyou.tavern.alleria.endpoint;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.alleria.configuration.TTLContext;
import com.tuoyou.tavern.protocol.alleria.dto.FileUploadDTO;
import com.tuoyou.tavern.alleria.common.service.FileUploadRecordService;
import com.tuoyou.tavern.protocol.alleria.model.FileUploadProcess;
import com.tuoyou.tavern.protocol.alleria.model.FileUploadRecord;
import com.tuoyou.tavern.protocol.alleria.response.FileUploadProcessResponse;
import com.tuoyou.tavern.protocol.alleria.response.FileUploadRecordResponse;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/file")
public class FileRecordEndpoint {

    private final FileUploadRecordService fileUploadRecordService;
    private final TTLContext ttlContext;

    /*
     * 文件上传列表查询
     * */
    //RESOURCE-PATH:/packages/{fileType}?page={page}&size={size}&status={status}
    @GetMapping(value = "/page")
    public FileUploadRecordResponse queryPackages(Page page, FileUploadDTO fileUploadDTO) {
        return new FileUploadRecordResponse(fileUploadRecordService.getRecordWithTypeAndStatusByPage(page, fileUploadDTO));

    }

    /*
     * 删除记录
     * */
    @DeleteMapping(value = "/delete/{batchId}")
    public TavernResponse deleteFileRecord(@PathVariable String batchId) {
        FileUploadRecord fileUploadRecord = new FileUploadRecord();
        fileUploadRecord.setBatchId(batchId);
        fileUploadRecord.setIsValid("0");
        fileUploadRecordService.updateById(fileUploadRecord);
        return new TavernResponse();

    }


    /*
     * 查询文件上传进度
     * */
    @GetMapping(value = "/percentage")
    public FileUploadProcessResponse queryInvoiceUploadPercentage(@RequestParam("batchId") String batchId) {
        FileUploadProcess process = FileUploadProcess.builder().percentage(0).status(0).build();
        JSONObject object = (JSONObject) ttlContext.getValue(batchId);
        if (object == null) {
            return new FileUploadProcessResponse(process);
        } else {
            process.setPercentage(object.getDouble("percentage"));
            process.setStatus(object.getInteger("status"));
        }
        log.info("batchId: {} pt: {}", batchId, process.getPercentage());
        return new FileUploadProcessResponse(process);
    }

}
