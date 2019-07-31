package com.tuoyou.tavern.protocol.alleria.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ExtraInvoiceDTO {

    private String customId;
    private String accountPeriod;

}