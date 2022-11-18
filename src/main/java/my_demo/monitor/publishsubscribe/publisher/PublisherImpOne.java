package my_demo.monitor.publishsubscribe.publisher;

import my_demo.monitor.publishsubscribe.subpub.SubscribePublish;

/**
 * @author Xander Wu
 * @date 2022/11/18 10:42
 */
public class PublisherImpOne<Msg> implements IPublisher<Msg> {
    private final String name;

    public PublisherImpOne(String name) {
        this.name = name;
    }

    /**
     * 弱依赖 SubscribePublish，并没有直接委托。
     * 仅在方法层面，将逻辑委托给了 SubscribePublish
     * @param subscribePublish
     * @param message
     * @param isInstantMsg
     */
    @Override
    public void publish(SubscribePublish subscribePublish, Msg message, boolean isInstantMsg) {
        subscribePublish.publish(this.name, message, isInstantMsg);
    }

}