package com.tuoyou.tavern.alleria.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tuoyou.tavern.protocol.alleria.common.FileUploadStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/03 <br>
 */
@Slf4j
public class CommonUtils {

    public static <T, P> IPage<P> newIPage(IPage<T> iPage, List<P> list) {
        Page<P> page = new Page<>();
        page.setRecords(list);
        page.setCurrent(iPage.getCurrent());
        page.setSize(iPage.getSize());
        page.setCurrent(iPage.getCurrent());
        page.setTotal(iPage.getTotal());
        return page;
    }

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
