package the_beauty_of_design_patterns.chapter60.demo.strategyfactory;

import the_beauty_of_design_patterns.chapter60.demo.strategy.Strategy;
import the_beauty_of_design_patterns.chapter60.demo.strategy.concrete.ConcreteStrategyA;
import the_beauty_of_design_patterns.chapter60.demo.strategy.concrete.ConcreteStrategyB;

/**
 * <p> "有状态" 策略类 </p>
 *
 * 一般来讲，如果策略类是 "无状态" 的，不包含成员变量，只是纯粹的算法实现，这样的策略对象是可以被共享使用的，
 * 不需要在每次调用 getStrategy() 的时候，都创建一个新的策略对象。
 * 针对这种情况，我们可以使用上面这种工厂类的实现方式，事先创建好每个策略对象，缓存到工厂类中，用的时候直接返回。
 *
 *
 *
 * 相反，如果策略类是 "有状态" 的，根据业务场景的需要，我们希望每次从工厂方法中，获得的都是 新创建的策略对象，而不是缓存好可共享的策略对象，
 * 那我们就需要按照如下方式来实现策略工厂类:
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/12
 * </pre>
 */

public class StrategyFactory2 {

    public static Strategy getStrategy(String type) {

        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }


        if (type.equals("A")) {
            return new ConcreteStrategyA(/*成员变量*/);
        } else if (type.equals("B")) {
            return new ConcreteStrategyB(/*成员变量*/);
        }

        return null;
    }
}