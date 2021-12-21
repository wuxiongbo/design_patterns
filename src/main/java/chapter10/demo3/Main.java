package chapter10.demo3;

/**
 * <p> 必须使用继承的场景 </p>
 *
 *  “继承” 主要有以下三个作用：
 *     1. 表示 is-a 关系
 *     2. 支持多态特性
 *     3. 代码复用
 *
 *  而这三个作用都可以通过 组合、接口、委托 三个技术手段来达成。
 *
 *  “组合”的优势：
 *      组合能解决层次过深、过复杂的继承关系影响代码可维护性的问题。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/30
 * </pre>
 */
public class Main {
    public static void main(String[] args){
        // 调用 我们重写后的类。
        FeignClient client = new CustomizedFeignClient();

        // 传入我们重写后的子类
        new Main().demofunction(client);
    }

    // 不能改变函数demofunction的入参类型，而入参又不是接口。
    // 这种情况下，为了支持多态，只能采用继承来实现。
    public void demofunction(FeignClient feignClient) {
        //...

        String url = "url";

        feignClient.encode(url);

        // ...
    }

}
