package com.tuoyou.tavern.protocol.alleria.response;

import com.tuoyou.tavern.protocol.alleria.model.Dict;
import com.tuoyou.tavern.protocol.common.TavernResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/7/12 <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class DictResponse extends TavernResponse {
    private List<Dict> data;
}
