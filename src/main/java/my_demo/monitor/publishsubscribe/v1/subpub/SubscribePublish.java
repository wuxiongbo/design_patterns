package my_demo.monitor.publishsubscribe.v1.subpub;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Getter;
import my_demo.monitor.publishsubscribe.v1.subcriber.ISubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 消息 订阅器
 *
 * @author Xander Wu
 * @date 2022/11/18 10:42
 */
public class SubscribePublish {

    @Getter
    //订阅器名称
    private final String name;

    //订阅器队列容量
    final int QUEUE_CAPACITY = 20;

    /**
     * 订阅器中，拥有 消息存储队列
     */
    private final BlockingQueue<Message> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    //订阅者
    private final List<ISubscriber> subscribers = new ArrayList<>();

    /**
     * 订阅消费任务
     */
    private final ThreadPoolExecutor threadPoolExecutor;


    public SubscribePublish(String name) {
        this.name = name;


        BlockingQueue<Runnable> workQueue = new SynchronousQueue<>();
        ThreadFactory factory = new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat("at-sub-task-%s")
                .build();
        this.threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                0, TimeUnit.SECONDS, workQueue, factory);

        subscribeMsg();

    }

    /**
     * 订阅消息
     * 单独一个线程，专门用来帮助 消息订阅者  消费消息。
     */
    private void subscribeMsg() {
        this.threadPoolExecutor.execute(() -> {

            // 订阅消息
            while (true) {

                try {
                    this.notifyMsg();
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }


    // 订阅，取消订阅
    public void subscribe(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unSubscribe(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }


    public <Msg> void publish(String publisherName, Msg msg, boolean block) {

        // <异步 阻塞>
        // 直接进行消息通知
        if (block) {
            // 及时消费消息，不缓存
            notifyMsg(publisherName, msg);
            return;
        }


        // <异步 非阻塞>
        // 消息存到队列，异步消费
        Message<Msg> message = new Message<>(publisherName, msg);
        // 入队消息队列，如果入队失败，说明队列满了。则需要等待
        if (!queue.offer(message)) {

            while (!queue.offer(message)) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }


    // 队列消费后 通知所有的 订阅者
    private <Msg> void notifyMsg() throws InterruptedException {

        // 消息消费。
        Message<Msg> message = null;
        while ((message = queue.take()) != null) {

            // 将取出的消息，通知 给订阅者。
            this.notifyMsg(message.getPublisher(), message.getMsg());

        }
    }


    // 通知所有的订阅者
    private <Msg> void notifyMsg(String publisher, Msg msg) {

        for (ISubscriber subscriber : subscribers) {

            try {
                subscriber.update(publisher, msg);
            } catch (Exception e) {
                // ignore
                System.out.println("类型转换异常," + subscriber.getName() + e.getMessage());
            }

        }


    }


}



