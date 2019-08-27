package com.tuoyou.tavern.protocol.common;

import com.tuoyou.tavern.protocol.common.model.Dict;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/27 <br>
 */
@Data
public class TavernDictResponse extends TavernResponse {

    public TavernDictResponse(List<Dict> data) {
        this.data = data;
    }

    private List<Dict> data = new ArrayList<>();
}
