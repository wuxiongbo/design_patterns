package demo16.bo;

import lombok.Data;

@Data
// 方法入参
public class ApiStatInfo {
    private String api;
    private long requestCount;
    private long errorCount;
    private long durationOfSeconds;
    private long timeoutCount; // 改动一：添加新字段
}
