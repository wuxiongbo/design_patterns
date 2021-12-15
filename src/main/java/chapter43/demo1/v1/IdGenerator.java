package chapter43.demo1.v1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>描述类的信息</p>
 *
 * 我们通过一个 HashMap 来存储对象，其中 key 是线程 ID，value 是对象。
 * 这样我们就可以做到，不同的线程对应不同的对象，同一个线程只能对应一个对象。
 * 实际上，Java 语言本身提供了 ThreadLocal 工具类，可以更加轻松地实现线程唯一单例。
 * 不过，ThreadLocal 底层实现原理也是基于下面代码中所示的 HashMap。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/15
 * </pre>
 */

public class IdGenerator {
    private AtomicLong id = new AtomicLong(0);


    private static final ConcurrentHashMap<Long, IdGenerator> INSTANCES = new ConcurrentHashMap<>();

    private IdGenerator() {}

    public static IdGenerator getInstance() {
        Long currentThreadId = Thread.currentThread().getId(); // 线程 ID

        INSTANCES.putIfAbsent(currentThreadId, new IdGenerator());

        return INSTANCES.get(currentThreadId);
    }

    public long getId() {
        return id.incrementAndGet();
    }

}
