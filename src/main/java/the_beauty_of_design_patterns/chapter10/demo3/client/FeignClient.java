package the_beauty_of_design_patterns.chapter10.demo3.client;

/**
 * <p> 必须使用 “继承” 的场景 </p>
 * FeignClient 是一个外部类，我们 没有权限 去 修改这部分代码 ，但是我们希望能 重写 这个类在 运行时 执行的 encode() 函数。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/30
 * </pre>
 */
public class FeignClient {

    //...省略其他代码...

    protected void encode(String url) {
        System.out.println("FeignClient.encode()");
    }

    //...省略其他代码...
}
