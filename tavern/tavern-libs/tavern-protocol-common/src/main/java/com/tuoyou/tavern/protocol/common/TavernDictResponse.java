package com.tuoyou.tavern.protocol.common;

import com.tuoyou.tavern.protocol.common.model.Dict;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/08/27 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class TavernDictResponse extends TavernResponse {
    public TavernDictResponse() {
        super();
    }

    private List<Dict> data = new ArrayList<>();
}
