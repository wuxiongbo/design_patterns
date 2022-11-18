package my_demo.monitor.publishsubscribe.subpub;

import lombok.Getter;
import my_demo.monitor.publishsubscribe.subcriber.ISubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 订阅器
 * @author Xander Wu
 * @date 2022/11/18 10:42
 */
public class SubscribePublish {

    @Getter
    //订阅器名称
    private final String name;

    //订阅器队列容量
    final int QUEUE_CAPACITY = 20;

    //订阅器 消息存储队列
    private final BlockingQueue<Message> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    //订阅者
    private final List<ISubscriber> subcribers = new ArrayList<>();

    public SubscribePublish(String name) {
        this.name = name;
    }

    // 维护订阅者
    public void subcribe(ISubscriber subcriber) {
        subcribers.add(subcriber);
    }

    public void unSubcribe(ISubscriber subcriber) {
        subcribers.remove(subcriber);
    }



    public <Msg> void publish(String publisherName, Msg msg, boolean isInstantMsg) {

        if (isInstantMsg) {
            // 及时消费消息，不缓存
            update(publisherName, msg);
            return;
        }

        Message<Msg> message = new Message<>(publisherName, msg);
        // 生产消息队列
        if (!queue.offer(message)) {
            // 消费消息队列
            update();
        }

    }



    // 队列消费后 通知订阅者
    private <Msg> void update() {
        Message m = null;
        while ((m = queue.peek()) != null) {
            this.update(m.getPublisher(), (Msg) m.getMsg());
        }
    }

    // 及时 通知订阅者
    private <Msg> void update(String publisher, Msg Msg) {
        for (ISubscriber subcriber : subcribers) {


            try {
                subcriber.update(publisher, Msg);
            } catch (Exception e) {
                //ignore
                System.out.println(e);
            }


        }
    }
}



