package com.tuoyou.tavern.protocol.hrm.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
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
public class StaffInfoPageResponse extends TavernResponse {
    private IPage<StaffBasicInfo> data;

}
