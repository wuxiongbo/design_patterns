package chapter60.demo.strategyfactory;

import chapter60.demo.strategy.Strategy;
import chapter60.demo.strategy.concrete.ConcreteStrategyA;
import chapter60.demo.strategy.concrete.ConcreteStrategyB;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>策略工厂</p>
 *
 * 因为 ‘策略模式’ 会包含 一组策略，在使用它们的时候，一般会通过类型（type）来判断创建哪个策略来使用。
 *
 * 所以， 为了 ‘封装’ 创建逻辑，对 客户端代码 屏蔽 创建细节。
 * 我们可以把 “根据 type 创建 策略” 的逻辑抽离出来，放到 ‘工厂类’ 中。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/12
 * </pre>
 */
public class StrategyFactory {

    private static final Map<String, Strategy> strategies = new HashMap<>();

    static {
        strategies.put("A", new ConcreteStrategyA());
        strategies.put("B", new ConcreteStrategyB());
    }



    public static Strategy getStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }
        return strategies.get(type);
    }
}
