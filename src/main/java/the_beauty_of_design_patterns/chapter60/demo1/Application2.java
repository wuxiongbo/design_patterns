package the_beauty_of_design_patterns.chapter60.demo1;

import the_beauty_of_design_patterns.chapter60.demo1.dependence.UserCache;
import the_beauty_of_design_patterns.chapter60.demo1.strategy.EvictionStrategy;
import the_beauty_of_design_patterns.chapter60.demo1.strategy.concrete.LruEvictionStrategy;

/**
 * <p> 非运行时 </p>
 *
 * 策略模式三部曲：
 *     定义策略        编写策略类
 *     创建策略        new 策略类
 *     使用策略        调用策略类
 *
 *
 * “非运行时动态确定”，并不能发挥  策略模式 的优势。
 *
 * 在这种应用场景下，策略模式  实际上 退化 成了 “面向对象的多态特性” 或 “基于接口而非实现编程原则”
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/12
 * </pre>
 */
public class Application2 {

    public static void main(String[] args) {

        // 非运行时 动态确定，在代码中指定使用哪种策略

        //...
        EvictionStrategy evictionStrategy = new LruEvictionStrategy();

        UserCache userCache = new UserCache(evictionStrategy);
        //...
    }
}
