package my_demo.monitor.publishsubscribe.publisher;

import my_demo.monitor.publishsubscribe.subpub.SubscribePublish;

/**
 * 发布者
 * @author Xander Wu
 * @date 2022/11/18 10:41
 */
public interface IPublisher<Msg> {
    void publish(SubscribePublish subscribePublish, Msg msg, boolean isInstantMsg);
}