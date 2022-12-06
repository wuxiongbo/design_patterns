package my_demo.monitor.publishsubscribe.v1.subcriber.impl;

import lombok.Getter;
import my_demo.monitor.publishsubscribe.v1.subcriber.ISubscriber;

/**
 *
 * 不指定泛型，会有泛型擦除的风险
 *
 * @author Xander Wu
 * @date 2022/11/18 10:43
 */
public class SubscriberImpX<Msg> implements ISubscriber<Msg> {

    @Getter
    public String name;

    public SubscriberImpX(String name) {
        super();
        this.name = name;
    }

    @Override
    public void update(String publisher, Msg msg) {
        System.out.println(this.name + "收到" + publisher + "发来的消息:" + msg);
    }

}