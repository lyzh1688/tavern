package com.tuoyou.tavern.invoice.common.libs.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/21 <br>
 */
public class ExcelListener<T> extends AnalysisEventListener<T> {

    private List<T> datas = new ArrayList<T>();

    @Override
    public void invoke(T object, AnalysisContext context) {
        datas.add(object);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }


}

