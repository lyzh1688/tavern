package com.tuoyou.tavern.crm.workflow.dto;

import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/09/06 <br>
 */
@Data
public class WorkFlowNextNodeDTO {
    private String eventId;
    private String preNodeId;
    private String curNodeId;
    private String curOperator;
    private String curOperatorName;
    private String operator;
    private String operatorName;
    private String message;
    private List<MultipartFile> files = Lists.newArrayList();
    private BigDecimal refundFee;


    private String thirdPartyFlag;
    private String thirdPartyId;
    private String thirdPartyInfo;
    private String thirdPartyFee;

    private boolean isDjfw;
    private String isTrust;
    private Integer employeeNum;
    private String months;
    private String beginDate;
    private String endDate;

    private String refundWay;

    public WorkFlowNextNodeDTO(String eventId, String preNodeId, String curNodeId, String curOperator, String curOperatorName, String operator, String operatorName, String message, List<MultipartFile> files, BigDecimal refundFee, String thirdPartyFlag, String thirdPartyId, String thirdPartyInfo, String thirdPartyFee) {
        this.eventId = eventId;
        this.preNodeId = preNodeId;
        this.curNodeId = curNodeId;
        this.curOperator = curOperator;
        this.curOperatorName = curOperatorName;
        this.operator = operator;
        this.operatorName = operatorName;
        this.message = message;
        this.files = files;
        this.refundFee = refundFee;
        this.thirdPartyFlag = thirdPartyFlag;
        this.thirdPartyId = thirdPartyId;
        this.thirdPartyInfo = thirdPartyInfo;
        this.thirdPartyFee = thirdPartyFee;
    }

    public WorkFlowNextNodeDTO(String eventId, String preNodeId, String curNodeId, String curOperator, String curOperatorName, String operator, String operatorName, String message, List<MultipartFile> files, BigDecimal refundFee) {
        this.eventId = eventId;
        this.preNodeId = preNodeId;
        this.curNodeId = curNodeId;
        this.curOperator = curOperator;
        this.curOperatorName = curOperatorName;
        this.operator = operator;
        this.operatorName = operatorName;
        this.message = message;
        this.files = files;
        this.refundFee = refundFee;
    }

    public WorkFlowNextNodeDTO(String eventId, String preNodeId, String curNodeId, String curOperator, String curOperatorName, String operator, String operatorName, String message, List<MultipartFile> files, BigDecimal refundFee, String thirdPartyFlag, String thirdPartyId, String thirdPartyInfo, String thirdPartyFee, boolean isDjfw, String isTrust, Integer employeeNum, String months, String beginDate, String endDate) {
        this.eventId = eventId;
        this.preNodeId = preNodeId;
        this.curNodeId = curNodeId;
        this.curOperator = curOperator;
        this.curOperatorName = curOperatorName;
        this.operator = operator;
        this.operatorName = operatorName;
        this.message = message;
        this.files = files;
        this.refundFee = refundFee;
        this.thirdPartyFlag = thirdPartyFlag;
        this.thirdPartyId = thirdPartyId;
        this.thirdPartyInfo = thirdPartyInfo;
        this.thirdPartyFee = thirdPartyFee;
        this.isDjfw = isDjfw;
        this.isTrust = isTrust;
        this.employeeNum = employeeNum;
        this.months = months;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public WorkFlowNextNodeDTO(String eventId, String preNodeId, String curNodeId, String curOperator, String curOperatorName, String operator, String operatorName, String message, List<MultipartFile> files, BigDecimal refundFee, String thirdPartyFlag, String thirdPartyId, String thirdPartyInfo, String thirdPartyFee, boolean isDjfw, String isTrust, Integer employeeNum, String months, String beginDate, String endDate, String refundWay) {
        this.eventId = eventId;
        this.preNodeId = preNodeId;
        this.curNodeId = curNodeId;
        this.curOperator = curOperator;
        this.curOperatorName = curOperatorName;
        this.operator = operator;
        this.operatorName = operatorName;
        this.message = message;
        this.files = files;
        this.refundFee = refundFee;
        this.thirdPartyFlag = thirdPartyFlag;
        this.thirdPartyId = thirdPartyId;
        this.thirdPartyInfo = thirdPartyInfo;
        this.thirdPartyFee = thirdPartyFee;
        this.isDjfw = isDjfw;
        this.isTrust = isTrust;
        this.employeeNum = employeeNum;
        this.months = months;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.refundWay = refundWay;
    }

    @Override
    public String toString() {
        return "WorkFlowNextNodeDTO{" +
                "eventId='" + eventId + '\'' +
                ", preNodeId='" + preNodeId + '\'' +
                ", curNodeId='" + curNodeId + '\'' +
                ", curOperator='" + curOperator + '\'' +
                ", curOperatorName='" + curOperatorName + '\'' +
                ", operator='" + operator + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", message='" + message + '\'' +
                ", files=" + files +
                ", refundFee=" + refundFee +
                ", thirdPartyFlag='" + thirdPartyFlag + '\'' +
                ", thirdPartyId='" + thirdPartyId + '\'' +
                ", thirdPartyInfo='" + thirdPartyInfo + '\'' +
                ", thirdPartyFee='" + thirdPartyFee + '\'' +
                ", isDjfw=" + isDjfw +
                ", isTrust='" + isTrust + '\'' +
                ", employeeNum=" + employeeNum +
                ", months='" + months + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", refundWay='" + refundWay + '\'' +
                '}';
    }
}
