package chapter10.demo1.birds;

import chapter10.demo1.ability.impl.EggLayAbility;
import chapter10.demo1.ability.impl.TweetAbility;
import chapter10.demo1.ability.EggLayable;
import chapter10.demo1.ability.Tweetable;

/**
 * <p>鸵鸟</p>
 *
 * 具备的能力：
 *    会叫、会下蛋
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/30
 * </pre>
 */
public class Ostrich implements Tweetable, EggLayable {

    //组合。 会叫的能力
    private TweetAbility tweetAbility = new TweetAbility();
    //组合。 会下蛋的能力
    private EggLayAbility eggLayAbility = new EggLayAbility();


    //... 省略其他属性和方法...
    @Override
    public void tweet() {
        // 实现...

        // 委托
        tweetAbility.tweet();
    }
    @Override
    public void layEgg() {
        // 实现...

        // 委托
        eggLayAbility.layEgg();
    }
}
