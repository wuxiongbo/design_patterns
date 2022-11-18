package my_demo.monitor.publishsubscribe.subcriber;

import my_demo.monitor.publishsubscribe.subpub.SubscribePublish;

/**
 * 订阅者
 * @author Xander Wu
 * @date 2022/11/18 10:42
 */
public interface ISubscriber<Msg> {
    public void subscribe(SubscribePublish subscribePublish);

    public void unSubscribe(SubscribePublish subscribePublish);

    public void update(String publisher, Msg message);
}
