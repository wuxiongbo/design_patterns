package my_demo.monitor.publishsubscribe.v1.subcriber.impl;

import lombok.Getter;
import my_demo.monitor.publishsubscribe.v1.subcriber.ISubscriber;

/**
 *
 * 泛型 指定消息类型。 达到，对指定类型的消息感兴趣的目的
 *
 * @author Xander Wu
 * @date 2022/11/18 10:43
 */
public class SubscriberImpOne implements ISubscriber<String> {

    @Getter
    public String name;

    public SubscriberImpOne(String name) {
        super();
        this.name = name;
    }

    @Override
    public void update(String publisher, String msg) {
        System.out.println(this.name + "收到" + publisher + "发来的消息:" + msg);
    }

}