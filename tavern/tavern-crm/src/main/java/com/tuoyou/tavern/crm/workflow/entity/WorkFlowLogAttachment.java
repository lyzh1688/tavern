package com.tuoyou.tavern.crm.workflow.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("workflow_log_attachment")
public class WorkFlowLogAttachment extends Model<WorkFlowLogAttachment> {
    @TableId
    private String fileId;

    private String logId;

    private String createTime;

    private String filePath;

}