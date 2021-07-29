package chapter45.bean;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/29
 * </pre>
 */
public class RedisCounter {
    private String ipAddress;
    private int port;
    public RedisCounter(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }
    //...
}
