package my_demo.monitor.publishsubscribe.subpub;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Getter;
import my_demo.monitor.publishsubscribe.subcriber.ISubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

        subscriptionTask();

    }


    private void subscriptionTask(){
        this.threadPoolExecutor.execute(()->{
            while (true){

                try {
                    this.update();
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    // 维护订阅者
    public void subscribe(ISubscriber subcriber) {
        subscribers.add(subcriber);
    }

    public void unSubscribe(ISubscriber subcriber) {
        subscribers.remove(subcriber);
    }



    public <Msg> void publish(String publisherName, Msg msg, boolean isInstantMsg) {

        if (isInstantMsg) {
            // 及时消费消息，不缓存
            update(publisherName, msg);
            return;
        }

        Message<Msg> message = new Message<>(publisherName, msg);
        // 入队消息队列
        if (!queue.offer(message)) {
            // 消费消息队列
            try {
                update();
            } catch (InterruptedException e) {

            }
        }

    }



    // 队列消费后 通知订阅者
    private <Msg> void update() throws InterruptedException {
        Message message = null;
        while ((message = queue.take()) != null) {
            this.update(message.getPublisher(), (Msg) message.getMsg());
        }
    }


    // 及时 通知订阅者
    private <Msg> void update(String publisher, Msg Msg) {
        for (ISubscriber subscriber : subscribers) {

            try {
                subscriber.update(publisher, Msg);
            } catch (Exception e) {
                //ignore
                System.out.println(e);
            }

        }
    }



}



