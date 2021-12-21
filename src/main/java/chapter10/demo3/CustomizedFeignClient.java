package chapter10.demo3;

/**
 * <p> 必须使用 “继承” 的场景 </p>
 *
 *  通过继承，实现方法的重写
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/30
 * </pre>
 */
public class CustomizedFeignClient extends FeignClient{
    @Override
    public void encode(String url) {
        //...重写encode的实现...
    }
}
