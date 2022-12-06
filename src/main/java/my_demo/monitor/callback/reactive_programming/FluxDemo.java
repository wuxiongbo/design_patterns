package my_demo.monitor.callback.reactive_programming;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * Flux  发布者
 *
 * LambdaSubscriber   订阅者
 *
 *
 * @author Xander Wu
 * @date 2022/12/6 13:34
 */
@Slf4j
public class FluxDemo {
    public static void main(String[] args) {
//        Flux.just("11", "22", "33")
//                .log()
//                .subscribe(System.out::println);



        Subscriber<String> subscriber = new Subscriber<>() {
            //在订阅成功的时候，如何操作
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




        Flux.just("test1", "test2", "test3")
                //打印详细流日志
                .log()
                //订阅消费
                .subscribe(subscriber);

    }
}
