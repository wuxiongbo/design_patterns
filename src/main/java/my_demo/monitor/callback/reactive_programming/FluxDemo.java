package my_demo.monitor.callback.reactive_programming;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * Flux 使用示例
 *
 * FluxArray            发布者
 * StrictSubscriber     订阅者     类似的 还有 LambdaSubscriber
 * ArraySubscription    订阅器
 *
 *
 * 背压的概念：
 * <a href="https://developer.aliyun.com/article/939660"> 关于『背压』这个错误翻译 </a>
 *
 * 响应式的应用——webflux
 * <a href="https://zhuanlan.zhihu.com/p/559158740"> 一文弄懂 Spring WebFlux 的来龙去脉 </a>
 *
 * @author Xander Wu
 * @date 2022/12/6 13:34
 */
@Slf4j
public class FluxDemo {
    public static void main(String[] args) {
        Flux.just("11", "22", "33")
                .log()
                .subscribe(System.out::println);


    }

    public static void simpleFluxTest(){
        // 订阅者
        Subscriber<String> subscriber = new Subscriber<>() {

            /**
             * 这里是，在订阅成功的时候，如何操作
             * @param subscription   订阅器
             *
             * {@link Subscription} that allows requesting data via {@link Subscription#request(long)}
             *
             */
            @Override
            public void onSubscribe(Subscription subscription) {
                //取最大数量的元素个数
                subscription.request(Long.MAX_VALUE);
            }

            //对于每个元素的操作
            @Override
            public void onNext(String o) {
                System.out.println(o);
            }

            //在发生错误的时候
            @Override
            public void onError(Throwable throwable) {
                log.error("error: {}", throwable.getMessage(), throwable);
            }

            //在完成的时候，发生错误不算完成
            @Override
            public void onComplete() {
                log.info("complete");
            }
        };




        /*
         * subscribe 方法的部分源码:
         *
         * public static <T> void subscribe(CoreSubscriber<? super T> s, T[] array) {
         *      省略....
         *
         *      s.onSubscribe(new ArraySubscription<>(s, array));
         *
         *      省略....
         * }
         *
         */


        // 发布者
        Flux
                // 构建 发布者，并 发布数据
                .just("test1", "test2", "test3")
                // 打印详细流日志
                .log()
                // 订阅 消费
                .subscribe(subscriber);
    }
}
