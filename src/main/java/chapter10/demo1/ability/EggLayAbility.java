package chapter10.demo1.ability;

import chapter10.demo1.interface1.EggLayable;

/**
 * <p>实现会下蛋的能力</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/30
 * </pre>
 */
public class EggLayAbility implements EggLayable {
    @Override
    public void layEgg() {
        System.out.println("下蛋（委托实现）");
    }
}
