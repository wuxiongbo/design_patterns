package demo10.demo1.imple;

import demo10.demo1.interface1.EggLayable;
import demo10.demo1.interface1.Flyable;
import demo10.demo1.interface1.Tweetable;

/**
 * <p>麻雀</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/30
 * </pre>
 */
public class Sparrow implements Flyable, Tweetable, EggLayable {
    //... 省略其他属性和方法...
    @Override
    public void fly() {
        //...
    }

    @Override
    public void tweet() {
        // ...
    }

    @Override
    public void layEgg() {
        // ...
    }
}


