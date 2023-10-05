package the_beauty_of_design_patterns.chapter56.demo1.observer.impl;

import the_beauty_of_design_patterns.chapter56.demo1.observer.Observer;
import the_beauty_of_design_patterns.chapter56.dependence.Message;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class ConcreteObserverTwo implements Observer {
    @Override
    public void update(Message message) {
        //TODO: 获取消息通知，执行自己的逻辑...
        System.out.println("ConcreteObserverTwo is notified.");
    }
}
