package my_demo.monitor.publishsubscribe.publisher;

import my_demo.monitor.publishsubscribe.subpub.SubscribePublish;

/**
 * 发布者
 * @author Xander Wu
 * @date 2022/11/18 10:41
 */
public interface IPublisher<Msg> {

    /**
     * 弱依赖 SubscribePublish，并没有直接委托。
     * 仅在方法层面，将逻辑委托给了 SubscribePublish
     * @param subscribePublish
     * @param msg
     * @param block             是否阻塞
     */
    void publish(SubscribePublish subscribePublish, Msg msg, boolean block);
}