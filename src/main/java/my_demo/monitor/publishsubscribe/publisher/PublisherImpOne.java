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

    @Override
    public void publish(SubscribePublish subscribePublish, Msg msg, boolean block) {
        subscribePublish.publish(this.name, msg, block);
    }

}