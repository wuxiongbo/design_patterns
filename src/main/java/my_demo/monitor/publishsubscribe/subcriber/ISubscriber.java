package my_demo.monitor.publishsubscribe.subcriber;

import my_demo.monitor.publishsubscribe.subpub.SubscribePublish;

/**
 * 订阅者
 *
 * @author Xander Wu
 * @date 2022/11/18 10:42
 */
public interface ISubscriber<Msg> {

    String getName();

    // 注册、注销
    default void subscribe(SubscribePublish subscribePublish){
        subscribePublish.subscribe(this);
    }
    default void unSubscribe(SubscribePublish subscribePublish) {
        subscribePublish.unSubscribe(this);
    }


    void update(String publisher, Msg message);
}
