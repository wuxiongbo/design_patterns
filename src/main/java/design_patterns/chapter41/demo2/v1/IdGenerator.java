package design_patterns.chapter41.demo2.v1;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> 饿汉式 </p>
 * 饿汉式的实现方式比较简单。
 * 在类加载的时候，INSTANCE 静态实例就已经创建并初始化好了，所以，INSTANCE 实例的创建过程是线程安全的。
 * 不过，这样的实现方式不支持延迟加载（在真正用到 IdGenerator 的时候，再创建实例），从名字中我们也可以看出这一点。
 *
 * 单例创建的对象，在本 虚拟机实例 中唯一。一个 虚拟机实例 视为一个进程，单例对象 在 一个进程内 的 多个线程间共享。
 *
 * 单例类中对象的唯一性的作用范围是进程内的，在进程间是不唯一的。
 *
 * 不支持延迟加载就代表不好吗？
 *
 * 有人觉得这种实现方式不好，因为不支持延迟加载，
 * 如果实例占用资源多（比如占用内存多）或初始化耗时长（比如需要加载各种配置文件），提前初始化实例是一种浪费资源的行为。
 * 最好的方法应该在用到的时候再去初始化。
 *
 * 我个人并不认同这样的观点。
 * 如果初始化耗时长，那我们最好不要等到真正要用它的时候，才去执行这个耗时长的初始化过程，这会影响到系统的性能
 * （比如，在响应客户端接口请求的时候，做这个初始化操作，会导致此请求的响应时间变长，甚至超时）。
 * 采用饿汉式实现方式，将耗时的初始化操作，提前到程序启动的时候完成，这样就能避免在程序运行的时候，再去初始化导致的性能问题。
 * 如果实例占用资源多，按照 fail-fast 的设计原则（有问题及早暴露），那我们也希望在程序启动时就将这个实例初始化好。
 * 如果资源不够，就会在程序启动的时候触发报错（比如 Java 中的 PermGen Space OOM），我们可以立即去修复。
 * 这样也能避免在程序运行一段时间后，突然因为初始化这个实例占用资源过多，导致系统崩溃，影响系统的可用性。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class IdGenerator {
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
