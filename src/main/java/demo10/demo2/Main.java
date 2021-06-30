package demo10.demo2;

/**
 * <p> 必须使用继承的场景 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/30
 * </pre>
 */
public class Main {
    public static void main(String[] args){
        // 调用
        FeignClient client = new CustomizedFeignClient();

        new Main()
                .demofunction(client);
    }

    // 不能改变函数demofunction的入参类型，而入参又非接口。
    // 这种情况下，为了支持多态，只能采用继承来实现。
    public void demofunction(FeignClient feignClient) {
        //...

        String url = "url";

        feignClient.encode(url);

        // ...
    }

}
