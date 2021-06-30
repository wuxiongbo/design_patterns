package demo10.demo2;

/**
 * <p>描述类的信息</p>
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
