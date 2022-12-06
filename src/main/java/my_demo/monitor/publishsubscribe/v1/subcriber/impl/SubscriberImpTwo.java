package my_demo.monitor.publishsubscribe.v1.subcriber.impl;

import lombok.Getter;
import my_demo.monitor.publishsubscribe.v1.subcriber.ISubscriber;

/**
 * 泛型 指定消息类型。 达到，对指定类型的消息感兴趣的目的
 *
 * @author Xander Wu
 * @date 2022/11/18 16:39
 */
public class SubscriberImpTwo implements ISubscriber<Long> {

    @Getter
    public String name;

    public SubscriberImpTwo(String name) {
        super();
        this.name = name;
    }

    @Override
    public void update(String publisher, Long msg) {
        System.out.println(this.name + "收到" + publisher + "发来的消息:" + msg.toString());
    }

}