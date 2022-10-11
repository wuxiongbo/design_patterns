package my_demo.bridg.v2.coffee.impl;

import my_demo.bridg.v2.additives.ICoffeeAdditives;
import my_demo.bridg.v2.coffee.RefinedCoffee;

/**
 * @author Xander Wu
 * @date 2022/9/27 18:06
 */
public class LargeCoffee extends RefinedCoffee {
    public LargeCoffee(ICoffeeAdditives additives) {
        super(additives);
    }

    @Override
    public void orderCoffee(int count) {

        // 调用委托
        additives.addSomething();

        System.out.printf("大杯咖啡%d杯%n",count);
    }
}
