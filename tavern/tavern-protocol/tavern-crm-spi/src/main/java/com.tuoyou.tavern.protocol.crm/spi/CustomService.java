package com.tuoyou.tavern.protocol.crm.spi;

import com.tuoyou.tavern.protocol.crm.response.CustomInfoResponse;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/11 <br>
 */
public interface CustomService {
    CustomInfoResponse getCustomInfo(String accnt, String password);
}
