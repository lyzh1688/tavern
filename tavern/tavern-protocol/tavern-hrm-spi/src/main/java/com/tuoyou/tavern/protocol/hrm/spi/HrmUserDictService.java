package com.tuoyou.tavern.protocol.hrm.spi;

import com.tuoyou.tavern.protocol.common.TavernDictResponse;
import com.tuoyou.tavern.protocol.common.annotation.PathParam;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface HrmUserDictService {

    TavernDictResponse queryStaffByRole(@PathParam(name = "roleId") String roleId);


}
