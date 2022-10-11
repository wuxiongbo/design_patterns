package my_demo.bridg.v2;

import my_demo.bridg.v2.additives.impl.Milk;
import my_demo.bridg.v2.coffee.impl.LargeCoffee;
import my_demo.bridg.v2.coffee.RefinedCoffee;

/**
 * 桥接模式
 *
 * 巧妙利用 组合关系 进行功能组合，避免了繁杂的继承
 *
 * <p>
 * <a href="https://github.com/shusheng007/design-patterns"/>
 *
 * @author Xander Wu
 * @date 2022/9/27 18:04
 */
public class Client {
    public static void main(String[] args) {
        // 数量： 点两杯
        // 属性1：加奶
        // 属性2：大杯咖啡
        RefinedCoffee largeWithMilk = new LargeCoffee(new Milk());

        // 点咖啡
        largeWithMilk.orderCoffee(2);

        // 检查咖啡的质量
        largeWithMilk.checkQuality();
    }
}
