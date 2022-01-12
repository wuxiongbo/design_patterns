package chapter41.demo2.v5;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> 枚举 </p>
 * 最后，我们介绍一种最简单的实现方式，基于枚举类型的单例实现。
 *
 * 这种实现方式通过 Java 枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性。
 *
 *
 * 总结：
 *  1. 单例的定义
 *      单例设计模式（Singleton Design Pattern）理解起来非常简单。
 *      一个类只允许创建一个对象（或者叫实例），那这个类就是一个单例类，这种设计模式就叫作单例设计模式，简称单例模式。
 *
 *  2. 单例的用处
 *      从业务概念上，有些数据在系统中只应该保存一份，就比较适合设计为单例类。
 *      比如，一些全局唯一类： 系统的配置信息类、连接池类(不推荐)、ID 生成器类。
 *      除此之外，我们还可以使用 单例 解决 资源访问冲突的问题。 callback_demo1/v3  日志案例
 *
 *  3. 单例的实现单例有下面几种经典的实现方式。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */

public enum IdGenerator {
    INSTANCE;

    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}
