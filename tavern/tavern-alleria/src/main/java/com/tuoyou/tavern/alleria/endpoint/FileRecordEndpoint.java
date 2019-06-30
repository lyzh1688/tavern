package com.tuoyou.tavern.alleria.endpoint;
import com.tuoyou.tavern.alleria.model.PackageUploadRecord;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘悦之 on 2019/6/29.
 */
@RestController
public class FileRecordEndpoint {

    /*
    * 文件上传列表查询
    * */
    //RESOURCE-PATH:/packages/{fileType}?page={page}&size={size}&status={status}
    @RequestMapping(value = "/alleria/packages/{fileType}",method = RequestMethod.GET,params = {"page","size","status"})
    public PackageUploadRecord queryPackages(@PathVariable String fileType, Integer  page, Integer size, String status) {
        return new PackageUploadRecord();
    }

}
