package com.tuoyou.tavern.protocol.hrm.response;

import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.hrm.model.StaffBasicInfo;
import lombok.Data;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@Data
public class StaffInfoResponse extends TavernResponse {
    private StaffBasicInfo data;
}
