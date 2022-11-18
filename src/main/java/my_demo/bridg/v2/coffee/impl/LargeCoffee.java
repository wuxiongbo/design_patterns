package my_demo.bridg.v2.coffee.impl;

import my_demo.bridg.v2.additives.ICoffeeAdditives;
import my_demo.bridg.v2.coffee.RefinedCoffee;

/**
 * 大杯咖啡
 * @author Xander Wu
 * @date 2022/9/27 18:06
 */
public class LargeCoffee extends RefinedCoffee {
    public LargeCoffee(ICoffeeAdditives additives) {
        super(additives);
    }

    @Override
    public void orderCoffee(int count) {

        // 委托 调用 additives的方法
        additives.addSomething();

        // 业务逻辑...
        System.out.printf("%d杯大杯咖啡，制作中...%n",count);

    }
}
