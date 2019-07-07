package com.tuoyou.tavern.crm.endpoint;

import com.tuoyou.tavern.protocol.common.TavernRequestAuthFields;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘悦之 on 2019/7/6.
 */
@RestController
public class TestEndpoint {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestHeader(value = TavernRequestAuthFields.USER_ACCNT) String userAccnt, @RequestHeader(value = TavernRequestAuthFields.USER_TYPE) String userType){
        return "hello " + userType+ " " + userAccnt;
    }
}
