package the_beauty_of_design_patterns.chapter10.demo3.client;

/**
 * @author Xander Wu
 * @date 2022/10/13 21:18
 */
public class Api {

    // 不能改变 demofunction()函数 的 入参类型，而入参又不是接口。
    //      这种情况下，只能采用 "继承" 来实现。
    public static void demofunction(FeignClient feignClient) {
        //...

        String url = "url";

        // 这里，利用了多态特性，调用的是我们重写后的子类
        feignClient.encode(url);

        // ...
    }
}
