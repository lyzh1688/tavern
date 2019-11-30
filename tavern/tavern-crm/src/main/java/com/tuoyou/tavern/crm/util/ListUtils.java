package com.tuoyou.tavern.crm.util;

import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFLowEdges;
import com.tuoyou.tavern.protocol.crm.model.workflow.WorkFLowNodes;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/11/30 <br>
 */
public class ListUtils {
    public static void sortWorkFLowEdgesFirstIndex(List<WorkFLowEdges> list, String key1, String key2) {
        if (null != list && list.size() > 0) {
            for (int index = 0, length = list.size(); index < length; index++) {
                if (list.get(index).getSource().contains(key1) || list.get(index).getSource().contains(key2)) {
                    WorkFLowEdges temp = list.get(0);
                    list.set(0, list.get(index));
                    list.set(index, temp);
                }
            }
        }
    }
    public static void sortWorkFLowNodesFirstIndex(List<WorkFLowNodes> list, String key1, String key2) {
        if (null != list && list.size() > 0) {
            for (int index = 0, length = list.size(); index < length; index++) {
                if (list.get(index).getLabel().contains(key1) || list.get(index).getLabel().contains(key2)) {
                    WorkFLowNodes temp = list.get(0);
                    list.set(0, list.get(index));
                    list.set(index, temp);
                }
            }
        }
    }
}
