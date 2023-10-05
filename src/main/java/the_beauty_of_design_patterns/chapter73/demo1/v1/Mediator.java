package the_beauty_of_design_patterns.chapter73.demo1.v1;

import the_beauty_of_design_patterns.chapter73.dependence.Component;

/**
 * <p> 中介 抽象</p>
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
