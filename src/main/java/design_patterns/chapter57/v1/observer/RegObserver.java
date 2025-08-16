package design_patterns.chapter57.v1.observer;

/**
 * <p> 注册 产生的事件 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public interface RegObserver {
    void handleRegSuccess(long userId);
}
