package the_beauty_of_design_patterns.chapter41.demo2.v2;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> 懒汉式 </p>
 *
 * 有饿汉式，对应的，就有懒汉式。懒汉式 相对于 饿汉式 的优势是  支持延迟加载。
 *
 * 不过懒汉式的缺点也很明显，我们给 getInstance() 这个方法加了一把大锁（synchronized），导致这个函数的并发度很低。
 *
 * 量化一下的话，并发度是 1，也就相当于串行操作了。
 * 而这个函数是在单例使用期间，一直会被调用。如果这个单例类偶尔会被用到，那这种实现方式还可以接受。
 * 但是，如果频繁地用到，那频繁加锁、释放锁及并发度低等问题，会导致性能瓶颈，这种实现方式就不可取了。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class IdGenerator {
    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator instance;

    private IdGenerator() {
    }

    // 方法锁。 该锁太重
    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
