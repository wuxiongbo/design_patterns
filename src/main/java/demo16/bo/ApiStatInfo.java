package demo16.bo;

import lombok.Data;

@Data
// 对方法的入参进行封装
public class ApiStatInfo {
    private String api;

    private long errorCount;

    // request 请求数量
    private long requestCount;
    // 持续的请求时间
    private long durationOfSeconds;

    private long timeoutCount; // 改动一：添加新字段
}
