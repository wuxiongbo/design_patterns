package design_patterns.chapter60.demo.strategy;

/**
 * <p> 策略接口 </p>
 *
 * 策略类 的定义比较简单，包含 一个 "策略接口" 和 一组 "策略实现类"。
 *
 * 因为所有的 策略类 都实现相同的 接口，所以，客户端代码基于 接口 而非 实现 编程，可以灵活地替换不同的策略
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/12
 * </pre>
 */
public interface Strategy {
    void algorithmInterface();
}
