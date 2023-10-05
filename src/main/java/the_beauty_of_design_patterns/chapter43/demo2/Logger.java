package the_beauty_of_design_patterns.chapter43.demo2;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>多例</p>
 *
 * 对于多例模式，还有一种理解方式：同一类型的只能创建一个对象，不同类型的可以创建多个对象。
 *
 * 这里的“类型”如何理解呢？
 *
 * 我们还是通过一个例子来解释一下，具体代码如下所示。
 * 在代码中，logger name 就是刚刚说的“类型”，同一个 logger name 获取到的对象实例是相同的，不同的 logger name 获取到的对象实例是不同的。
 *
 *
 * 这种多例模式的理解方式有点类似工厂模式。
 * 它跟工厂模式的不同之处是，多例模式创建的对象都是同一个类的对象，而工厂模式创建的是不同子类的对象，关于这一点，下一节课中就会讲到。
 *
 * 实际上，它还有点类似享元模式，两者的区别等到我们讲到享元模式的时候再来分析。
 *
 * 除此之外，实际上，枚举类型也相当于多例模式，一个类型只能对应一个对象，一个类可以创建多个对象。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/15
 * </pre>
 */

public class Logger {

    private static final ConcurrentHashMap<String, Logger> instances = new ConcurrentHashMap<>();

    private Logger() {}

    public static Logger getInstance(String loggerName) {
        instances.putIfAbsent(loggerName, new Logger());
        return instances.get(loggerName);
    }

    public void log() {
        //...
    }



    public static void main(String[] args){
        //l1==l2, l1!=l3
        Logger l1 = Logger.getInstance("User.class");
        Logger l2 = Logger.getInstance("User.class");
        Logger l3 = Logger.getInstance("Order.class");
    }
}


