package chapter73.demo1.v1;

import chapter73.dependence.Component;

/**
 * <p> 中介 </p>
 *
 * Mediator
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/17
 * </pre>
 */
public interface Mediator {
    void handleEvent(Component component, String event);
}
