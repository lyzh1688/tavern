package com.tuoyou.tavern.common.core.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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





}
