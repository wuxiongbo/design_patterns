package demo10.demo1.imple;

import demo10.demo1.ability.EggLayAbility;
import demo10.demo1.ability.TweetAbility;
import demo10.demo1.interface1.EggLayable;
import demo10.demo1.interface1.Tweetable;

/**
 * <p>鸵鸟</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/30
 * </pre>
 */
public class Ostrich  implements Tweetable, EggLayable {

    //组合
    private TweetAbility tweetAbility = new TweetAbility();
    //组合
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
