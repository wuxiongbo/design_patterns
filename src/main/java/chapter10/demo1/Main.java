package chapter10.demo1;

import chapter10.demo1.birds.Ostrich;
import chapter10.demo1.birds.Sparrow;

/**
 * <p>组合关系</p>
 *
 *  如果持续增加能力，组合爆炸，类的 继承层次 会越来越深、继承关系会越来越复杂
 *
 *                      AbstractBird
 *                      /          \
 *       AbstractFlyableBird     AbstractUnFlyableBird
 *         /              |          |              \
 *     Sparrow          Crow      Penguin          Ostrich
 *
 * 可变化的维度有： 是否会“下蛋”，是否会“飞”，是否会“叫”
 *
 *
 * Ability     能力的实现
 * interfaces  能力的接口
 *
 * impl        具体业务实现类
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/27
 * </pre>
 */
public class Main {
    public static void main(String[] args){
        // 麻雀
        Sparrow Sparrow = new Sparrow();
        Sparrow.fly();
        Sparrow.layEgg();
        Sparrow.tweet();

        // 鸵鸟
        Ostrich Ostrich = new Ostrich();
        Ostrich.layEgg();
        Ostrich.tweet();

    }
}
