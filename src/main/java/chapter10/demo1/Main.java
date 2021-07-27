package chapter10.demo1;

import chapter10.demo1.imple.Ostrich;
import chapter10.demo1.imple.Sparrow;

/**
 * <p>组合关系</p>
 *
 * Ability     能力的实现
 * interface1  能力的接口
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
