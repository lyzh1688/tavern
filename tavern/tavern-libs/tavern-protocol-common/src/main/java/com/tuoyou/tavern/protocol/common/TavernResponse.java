package com.tuoyou.tavern.protocol.common;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@Builder
@ToString
@Accessors(chain = true)
public class TavernResponse {
    @Getter
    @Setter
     int retCode = RetCode.SUCCESS;
    @Getter
    @Setter
     String retMessage = "ok";

    public TavernResponse(int retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public TavernResponse() {
    }
}
