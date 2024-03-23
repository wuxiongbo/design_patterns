package my_demo.bridg.v2;

import my_demo.bridg.v2.additives.impl.Milk;
import my_demo.bridg.v2.coffee.impl.LargeCoffee;
import my_demo.bridg.v2.coffee.RefinedCoffee;

/**
 * 桥接模式
 * <p>
 * 巧妙利用 组合关系 进行 功能组合，避免了繁杂的继承
 * <p>
 * 将 功能，从 咖啡杯大小、添加剂  两个维度拆开。
 *
 * <p>
 * <a href="https://github.com/shusheng007/design-patterns"/>
 *
 * @author Xander Wu
 * @date 2022/9/27 18:04
 */
public class Client {
    public static void main(String[] args) {
        // 功能属性1：加奶
        // 功能属性2：大杯咖啡
        RefinedCoffee largeWithMilk = new LargeCoffee(new Milk());


        System.out.println("=================");
        // 组合行为： 点两杯  大杯加奶咖啡
        largeWithMilk.orderCoffee(2);
        System.out.println("=================");


        // 抽象类 新增功能： 检查咖啡的质量参数
        largeWithMilk.checkQuality();

    }
}
