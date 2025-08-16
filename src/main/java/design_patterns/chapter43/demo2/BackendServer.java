package design_patterns.chapter43.demo2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>多例</p>
 *
 * “多例”指的就是，一个类可以创建多个对象，但是个数是有限制的，比如只能创建 3 个对象。
 *
 * 如果用代码来简单示例一下的话，就是下面这个样子：
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/15
 * </pre>
 */
public class BackendServer {
    private long serverNo;
    private String serverAddress;

    private static final int SERVER_COUNT = 3;
    private static final Map<Long, BackendServer> serverInstances = new HashMap<>();

    static {
        serverInstances.put(1L, new BackendServer(1L, "192.134.22.138:8080"));
        serverInstances.put(2L, new BackendServer(2L, "192.134.22.139:8080"));
        serverInstances.put(3L, new BackendServer(3L, "192.134.22.140:8080"));
    }

    private BackendServer(long serverNo, String serverAddress) {
        this.serverNo = serverNo;
        this.serverAddress = serverAddress;
    }

    public BackendServer getInstance(long serverNo) {
        return serverInstances.get(serverNo);
    }

    public BackendServer getRandomInstance() {
        Random r = new Random();
        int no = r.nextInt(SERVER_COUNT)+1;
        return serverInstances.get(no);
    }
}
