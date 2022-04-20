package chapter29.demo1.dependence;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>静态内部类</p>
 *
 * 我们再来看一种比双重检测更加简单的实现方法，那就是利用 Java 的静态内部类。
 *
 * 它有点类似饿汉式，但又能做到了延迟加载。
 *
 * SingletonHolder 是一个静态内部类，当外部类 IdGenerator 被加载的时候，并不会创建 SingletonHolder 实例对象。
 *
 * 只有当调用 getInstance() 方法时，SingletonHolder 才会被加载，这个时候才会创建 INSTANCE。
 * INSTANCE 的唯一性、创建过程的线程安全性，都由 JVM 来保证。
 * 所以，这种实现方法既保证了线程安全，又能做到延迟加载。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class IdGenerator {
    private static  AtomicLong id = new AtomicLong(0);

    private IdGenerator() {
    }

    // 外部类 IdGenerator 被加载的时候，并不会创建 SingletonHolder 实例对象
    private static class SingletonHolder {
        private static final IdGenerator INSTANCE = new IdGenerator();
    }


    public static IdGenerator getInstance() {
        // 只有调用 SingletonHolder时，SingletonHolder 才会被加载，这个时候才会创建 INSTANCE
        return SingletonHolder.INSTANCE;
    }

    public static String generateTransactionId() {
        return id.incrementAndGet()+"";
    }
}
