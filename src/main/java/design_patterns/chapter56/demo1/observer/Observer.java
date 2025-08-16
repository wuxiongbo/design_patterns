package design_patterns.chapter56.demo1.observer;

import design_patterns.chapter56.dependence.Message;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public interface Observer {
    void update(Message message);
}

