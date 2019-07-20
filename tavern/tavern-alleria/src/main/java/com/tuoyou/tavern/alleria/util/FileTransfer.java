package com.tuoyou.tavern.alleria.util;

import lombok.Builder;
import lombok.Data;

/**
 * Code Monkey: 何彪 <br>
 * Dev Time: 2019/07/14 <br>
 */
@Builder
@Data
public class FileTransfer {
    private String batchId;
    private String destLocation;
    private String financeDiskType;
}
