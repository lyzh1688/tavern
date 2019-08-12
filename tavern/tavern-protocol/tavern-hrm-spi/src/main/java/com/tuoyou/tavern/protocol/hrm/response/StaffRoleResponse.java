package com.tuoyou.tavern.protocol.hrm.response;

import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.hrm.model.HrmRoleInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by 刘悦之 on 2019/6/30.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class StaffRoleResponse extends TavernResponse {
    private List<HrmRoleInfo> data;

}
