package chapter10.demo1.imple;

import chapter10.demo1.ability.FlyAbility;
import chapter10.demo1.interface1.EggLayable;
import chapter10.demo1.interface1.Flyable;
import chapter10.demo1.interface1.Tweetable;

/**
 * <p>麻雀</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/30
 * </pre>
 */
public class Sparrow implements Flyable, Tweetable, EggLayable {

    // 组合。 会飞的能力
    private FlyAbility flyAbility = new FlyAbility();

    //... 省略其他属性和方法...
    @Override
    public void fly() {
        //...

        // 委托给组合类去实现
        flyAbility.fly();
    }

    @Override
    public void tweet() {
        // ...
        System.out.println("叫(麻雀实现)");
    }

    @Override
    public void layEgg() {
        // ...
        System.out.println("下蛋(麻雀实现)");
    }
}


