package com.tuoyou.tavern.protocol.hrm.response;

import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class StaffInfoResponse extends TavernResponse {
    public StaffInfoResponse() {
        super();
    }

    private StaffBasicInfo data;
}
