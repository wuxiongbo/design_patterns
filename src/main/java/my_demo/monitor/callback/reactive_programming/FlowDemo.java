package my_demo.monitor.callback.reactive_programming;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * <p>响应流的使用</p>
 *
 * SubmissionPublisher 发布-订阅 框架 的基本使用
 *
 * @author wuxiongbo
 * @date 2022/7/6 11:04
 */
public class FlowDemo {

    public static void main(String[] args) throws Exception {
        // 1. 定义 发布者(生产者), 发布的数据类型是 Integer
        //    直接使用jdk自带的SubmissionPublisher, 它实现了 Publisher 接口。
        // SubmissionPublisher里有一个数据缓冲区，用于缓冲发布者产生的数据，
        // 而这个缓冲区是利用一个Object数组实现的，缓冲区最大长度为256。
        // 我们可以在onSubscribe方法里打上断点，查看到这个缓冲区：
        SubmissionPublisher<Integer> publiser = new SubmissionPublisher<>();

        // 2. 定义 订阅者（消费者）
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<>() {

            /**
             * Subscription相当于是连接Publisher和Subscriber的“纽带”。
             * 当发布者调用subscribe方法注册订阅者时，
             * 会通过订阅者的回调方法onSubscribe传入Subscription对象，
             * 之后，订阅者就可以使用这个Subscription对象的request方法向发布者“要”数据了。
             * 背压机制 正是基于此来实现的。 背压(反压) back pressure
             */
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("保存订阅关系");

                // 保存订阅关系, 需要用它来给发布者响应
                this.subscription = subscription;

                // 请求一个数据。  流量控制
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                // 接受到一个数据, 处理
                System.out.println("接受到数据: " + item);

                // 处理完调用request再请求一个数据。  流量控制
                this.subscription.request(1);

                // 或者已经达到了目标, 可以调用cancel告诉发布者不再接受数据了
                // this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                // 出现了异常(例如处理数据的时候产生了异常)
                throwable.printStackTrace();

                // 我们可以告诉发布者, 后面不接受数据了
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                // 全部数据处理完了(发布者关闭了)
                System.out.println("处理完了!");
            }

        };

        // 3. 发布者和订阅者 建立订阅关系。  回调 onSubscribe 方法
        publiser.subscribe(subscriber);

        // 4. 生产数据, 并发布
        // 这里忽略数据生产过程
        for (int i = 0; i < 3; i++) {
            System.out.println("生成数据:" + i);
            // submit是个block方法
            publiser.submit(i);
        }

        // 5. 结束后 关闭发布者
        // 正式环境 应该放 finally 或者使用 try-resouce 确保关闭
        publiser.close();

        // 主线程延迟停止, 否则数据没有消费就会退出
        Thread.currentThread().join(1000);

        // debug的时候, 下面这行需要有断点
        // 否则主线程结束无法debug
        System.out.println();
    }

}
