package my_demo.monitor.callback.reactive_programming;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * <p>响应流 Flow 的简单使用</p>
 *
 * SubmissionPublisher 发布-订阅 框架 的基本使用
 * 队列长度默认为 256
 *
 *
 * @see  SubmissionPublisher
 * SubmissionPublisher{
 *     //缓存 订阅器
 *     BufferedSubscription<T> clients;
 * }
 *
 *
 *
 * <a href="https://www.jb51.net/article/240906.htm"> java9新特性 Reactive Stream 响应式编程 API </a>
 *
 * @author wuxiongbo
 * @date 2022/7/6 11:04
 */
public class FlowDemo {

    public static void main(String[] args) throws Exception {




    }


    public static void simpleTest() throws InterruptedException {

        // 1. 定义 发布者(生产者), 发布的数据类型是 Integer，
        //    本示例，直接使用jdk自带的 SubmissionPublisher 类， 它实现了 Publisher 接口。
        //
        // SubmissionPublisher 里有一个数据缓冲区，用于缓冲发布者产生的数据，
        // 而这个缓冲区，是利用一个 Object数组 实现的，缓冲区 最大长度为256。
        // 我们可以在 onSubscribe方法 里打上断点，查看到这个缓冲区：
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();



        // 2. 定义 订阅者（消费者）
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<>() {

            /**
             * 订阅器：
             *
             * Subscription 相当于，连接 Publisher 和 Subscriber 的“纽带”。
             * 当 '发布者' Publisher 调用 subscribe 方法注册 '订阅者' Subscriber  时，
             * 会通过 '订阅者' Subscriber 的回调方法 onSubscribe ，传入 '订阅器' Subscription，
             * 之后，'订阅者' Subscriber 就可以使用这个 '订阅器' Subscription 的 request 方法， 向 '发布者' Publisher “要” 数据了。
             *
             * "背压机制" 正是基于此来实现的。
             * 背压(反压) back pressure
             *
             * 说明：
             * 订阅器 的初始化是这段代码
             * BufferedSubscription<T> subscription =
             *                new BufferedSubscription<T>(
             *                      subscriber,
             *                      executor,
             *                      onNextHandler,
             *                      array,
             *                      max);
             *
             */
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("callback 1 保存订阅关系");

                // 保存订阅关系, 需要用它来给发布者响应
                this.subscription = subscription;

                // 请求一个数据。  流量控制
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                // 接受到一个数据, 处理
                System.out.println("callback 2 接受到数据: " + item);

                // 处理完调用request，再请求一个数据。  流量控制
                this.subscription.request(1);

                // 或者已经达到了目标, 可以调用cancel告诉发布者不再接受数据了
                // this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                // 出现了异常(例如处理数据的时候产生了异常)
                throwable.printStackTrace();

                // 告诉发布者, 后面不接受数据了
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                // 全部数据处理完了(发布者关闭了)
                System.out.println("callback 3 处理完了!");
            }

        };



        // 3. '发布者' 和 '订阅者' 建立 订阅关系。  部分源码如下， 回调 onSubscribe 方法:
        //   subscription.onSubscribe();
        //   if ((ex = closedException) != null)
        //       subscription.onError(ex);
        //   else if (closed)
        //       subscription.onComplete();

        publisher.subscribe(subscriber);



        // 4. 生产 消息数据、 发布 消息数据。
        //   内部，会将 消息 缓存到 "订阅器" 里面的 数组(队列) 中
        //   这里简化数据的生产过程
        for (int i = 0; i < 3; i++) {
            System.out.println("生成数据:" + i);
            // submit 是个 block方法
            publisher.submit(i);
        }

        // 5. 结束后 关闭发布者
        // 正式环境 应该放 finally 或者使用 try-resource 确保关闭
        publisher.close();




        // 主线程延迟停止, 否则数据没有消费就会退出
        Thread.currentThread().join(10000L);

        // debug的时候, 下面这行需要有断点
        // 否则主线程结束无法debug
        System.out.println();
    }

    public static void backPressureTest() {

        // 发布者
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();


        // 订阅者
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {

            /**
             * 订阅器
             * {@link SubmissionPublisher.BufferedSubscription}
             */
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                //向数据发布者请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("接收到 publisher 发来的消息了：" + item);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                //出现异常，就会来到这个方法，此时直接取消订阅即可
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                //发布者的所有数据都被接收，并且发布者已经关闭
                System.out.println("数据接收完毕");
            }
        };



        // 添加 订阅者。 这一步会在 发布者中 创建并缓存 一份 订阅器
        publisher.subscribe(subscriber);



        // 发布500条消息
        for (int i = 0; i < 500; i++) {
            System.out.println("i--------->" + i);
            publisher.submit("hello:" + i);
        }


        //关闭发布者
        publisher.close();
    }

}
