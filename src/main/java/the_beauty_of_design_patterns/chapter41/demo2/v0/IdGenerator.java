package the_beauty_of_design_patterns.chapter41.demo2.v0;

import java.util.concurrent.atomic.AtomicLong;


/**
 * <p>单例模式</p>
 *
 * 实战案例二：表示全局唯一类
 *
 * 从业务概念上，如果有些数据在系统中只应保存一份，那就比较适合设计为单例类。
 *
 * 比如，配置信息类。在系统中，我们只有一个配置文件，当配置文件被加载到内存之后，以对象的形式存在，也理所应当只有一份。
 *
 * 再比如，唯一递增 ID 号码生成器（第 34 讲中我们讲的是唯一 ID 生成器，这里讲的是唯一递增 ID 生成器），
 * 如果程序中有两个对象，那就会存在生成重复 ID 的情况，所以，我们应该将 ID 生成器类设计为单例。
 *
 *
 * 实际上，今天讲到的两个代码实例（Logger、IdGenerator），设计的都并不优雅，还存在一些问题。
 * 至于有什么问题以及如何改造，下一节课我会详细讲解。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class IdGenerator {
    // AtomicLong是一个Java并发库中提供的一个原子变量类型,
    // 它将一些线程不安全需要加锁的复合操作封装为了线程安全的原子操作，
    // 比如，下面会用到的incrementAndGet().
    private AtomicLong id = new AtomicLong(0);
    private static final IdGenerator INSTANCE = new IdGenerator();

    private IdGenerator() {
    }

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}