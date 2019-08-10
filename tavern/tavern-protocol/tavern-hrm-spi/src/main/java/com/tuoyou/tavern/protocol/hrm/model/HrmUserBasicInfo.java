package com.tuoyou.tavern.protocol.hrm.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("HRM_USER_BASIC_INFO")
public class HrmUserBasicInfo extends Model<HrmUserBasicInfo> {
    @TableId
    private String userId;

    private String userName;

    private String userAccnt;

    private String password;

    private String salt;

    private String isValid;

    private LocalDateTime updateDate;

}