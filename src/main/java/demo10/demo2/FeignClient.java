package demo10.demo2;

/**
 * <p>描述类的信息</p>
 * FeignClient 是一个外部类，我们没有权限去修改这部分代码，但是我们希望能重写这个类在运行时执行的 encode() 函数。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/30
 * </pre>
 */
public class FeignClient {

    //...省略其他代码...

    protected void encode(String url) {
        //...
    }

    //...省略其他代码...
}
