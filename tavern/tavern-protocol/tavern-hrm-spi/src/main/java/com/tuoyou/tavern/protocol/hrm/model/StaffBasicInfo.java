package com.tuoyou.tavern.protocol.hrm.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘悦之 on 2019/7/5.
 */
@Data
public class StaffBasicInfo {
    String userId;
    String userAccnt;
    String userName;
    List<String> roleList = new ArrayList<>();

}
