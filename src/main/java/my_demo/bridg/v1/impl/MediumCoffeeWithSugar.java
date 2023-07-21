package my_demo.bridg.v1.impl;

import my_demo.bridg.v1.ICoffee;

/**
 * 中杯加糖
 * @author Xander Wu
 * @date 2022/9/27 17:58
 */
public class MediumCoffeeWithSugar implements ICoffee {
    @Override
    public void orderCoffee(int count) {
        System.out.printf("中杯加糖咖啡%d杯%n",count);
    }
}
