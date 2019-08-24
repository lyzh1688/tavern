package com.tuoyou.tavern.alleria.util;

import com.alibaba.fastjson.JSONObject;
import com.tuoyou.tavern.protocol.alleria.common.FileUploadStatus;

import java.math.BigDecimal;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/24 <br>
 */
public class FileUtils {
    public static JSONObject percentageRecord(int index, int length) {
        JSONObject fileUploadObject = new JSONObject();
        fileUploadObject.put("status", FileUploadStatus.IN_PROCESS);
        double percentage = new BigDecimal((index + 1))
                .divide(new BigDecimal(length), 2, BigDecimal.ROUND_HALF_EVEN)
                .multiply(new BigDecimal(80))
                .add(new BigDecimal(20)).doubleValue();
        fileUploadObject.put("percentage", percentage);
        return fileUploadObject;
    }
}
