package demo10.demo1.ability;

import demo10.demo1.interface1.Flyable;

/**
 * <p>实现会飞的能力</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/6/30
 * </pre>
 */
public class FlyAbility implements Flyable {
    @Override
    public void fly() {
        System.out.println("飞（委托实现）");
    }
}
