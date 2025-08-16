package design_patterns.chapter60.demo1.strategyfactory;

import design_patterns.chapter60.demo1.strategy.EvictionStrategy;
import design_patterns.chapter60.demo1.strategy.concrete.LruEvictionStrategy;
import design_patterns.chapter60.demo1.strategy.concrete.FifoEvictionStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>策略工厂</p>
 *
 * 因为策略模式会包含一组策略，在使用它们的时候，一般会通过类型（type）来判断创建哪个策略来使用。
 *
 * 为了 封装 创建逻辑，我们需要对 客户端代码 屏蔽 创建细节。
 * 我们可以把 根据 type 创建 策略 的逻辑抽离出来，放到工厂类中。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/12
 * </pre>
 */
public class EvictionStrategyFactory {

    private static final Map<String, EvictionStrategy> strategies = new HashMap<>();

    static {
        strategies.put("A", new LruEvictionStrategy());
        strategies.put("B", new FifoEvictionStrategy());
    }



    public static EvictionStrategy getEvictionStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }
        return strategies.get(type);
    }
}
