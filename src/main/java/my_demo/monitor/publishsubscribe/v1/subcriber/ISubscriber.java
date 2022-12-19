package my_demo.monitor.publishsubscribe.v1.subcriber;

import my_demo.monitor.publishsubscribe.v1.subpub.SubscribePublish;

/**
 * 订阅者
 *
 * @author Xander Wu
 * @date 2022/11/18 10:42
 */
public interface ISubscriber<Msg> {

    String getName();

    /**
     * 注册
     * @param subscribePublish
     */
    default void subscribe(SubscribePublish subscribePublish){
        subscribePublish.subscribe(this);
    }

    /**
     * 注销
     * @param subscribePublish
     */
    default void unSubscribe(SubscribePublish subscribePublish) {
        subscribePublish.unSubscribe(this);
    }


    void update(String publisher, Msg message);
}
