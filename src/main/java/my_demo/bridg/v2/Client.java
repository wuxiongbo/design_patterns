package my_demo.bridg.v2;

import my_demo.bridg.v2.additives.Milk;
import my_demo.bridg.v2.coffee.LargeCoffee;
import my_demo.bridg.v2.coffee.RefinedCoffee;

/**
 * 桥接模式
 * <p>
 *
 * <a href="https://github.com/shusheng007/design-patterns"/>
 *
 * @author Xander Wu
 * @date 2022/9/27 18:04
 */
public class Client {
    public static void main(String[] args) {
        // 点两杯加奶的大杯咖啡
        RefinedCoffee largeWithMilk = new LargeCoffee(new Milk());

        // 点咖啡
        largeWithMilk.orderCoffee(2);

        // 检查咖啡的质量
        largeWithMilk.checkQuality();
    }
}
