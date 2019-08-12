package com.tuoyou.tavern.protocol.hrm.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.hrm.model.HrmRoleInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class StaffRolePageResponse extends TavernResponse {
    private IPage<HrmRoleInfo> data;

}
