package the_beauty_of_design_patterns.chapter41.demo2.v3;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> 双重检测 (实现懒加载)</p>
 *
 * 饿汉式不支持延迟加载，懒汉式有性能问题，不支持高并发。
 * 那我们再来看一种既支持延迟加载、又支持高并发的单例实现方式，也就是双重检测实现方式。
 * 在这种实现方式中，只要 instance 被创建之后，即便再调用 getInstance() 函数也不会再进入到加锁逻辑中了。
 * 所以，这种实现方式解决了懒汉式并发度低的问题。
 *
 * 网上有人说，这种实现方式有些问题。
 * 因为指令重排序，可能会导致 IdGenerator 对象被 new 出来，并且赋值给 instance 之后，
 * 还没来得及初始化（执行构造函数中的代码逻辑），就被另一个线程使用了。
 *
 * 要解决这个问题，我们需要给 instance 成员变量加上 volatile 关键字，禁止指令重排序才行。
 * 实际上，只有很低版本的 Java 才会有这个问题。
 * 我们现在用的高版本的 Java 已经在 JDK 内部实现中解决了这个问题
 * （解决的方法很简单，只要把对象 new 操作和初始化操作设计为原子操作，就自然能禁止重排序）。
 * 关于这点的详细解释，跟特定语言有关，我就不展开讲了，感兴趣的同学可以自行研究一下。
 *
 *  <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class IdGenerator {
    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator instance;

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        // 多一次判断，避免
        if (instance == null) {
            synchronized (IdGenerator.class) { // 此处为类级别的锁
                if (instance == null) {
                    instance = new IdGenerator();
                }
            }
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
