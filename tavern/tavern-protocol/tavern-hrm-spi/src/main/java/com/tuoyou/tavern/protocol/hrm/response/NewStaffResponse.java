package com.tuoyou.tavern.protocol.hrm.response;

import com.tuoyou.tavern.protocol.common.TavernResponse;
import com.tuoyou.tavern.protocol.hrm.model.NewStaffInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by 刘悦之 on 2019/7/5.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class NewStaffResponse extends TavernResponse{

    NewStaffInfo staffInfo;

}
