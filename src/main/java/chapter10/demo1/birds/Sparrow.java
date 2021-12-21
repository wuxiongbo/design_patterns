package chapter10.demo1.birds;

import chapter10.demo1.ability.impl.FlyAbility;
import chapter10.demo1.ability.EggLayable;
import chapter10.demo1.ability.Flyable;
import chapter10.demo1.ability.Tweetable;

/**
 * <p>麻雀</p>
 *
 * 具备的能力：
 *    会飞、会叫、会下蛋
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

        // 委托 给 组合类 去实现
        flyAbility.fly();
    }

    @Override
    public void tweet() {
        // ...

        // 自己实现
        System.out.println("叫(麻雀实现)");
    }

    @Override
    public void layEgg() {
        // ...

        // 自己实现
        System.out.println("下蛋(麻雀实现)");
    }
}


