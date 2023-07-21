package my_demo.bridg.v1.impl;

import my_demo.bridg.v1.ICoffee;

/**
 * 原味咖啡
 * @author Xander Wu
 * @date 2022/9/27 17:56
 */
public class CoffeeOriginal implements ICoffee {
    @Override
    public void orderCoffee(int count) {
        System.out.printf("原味咖啡%d杯%n",count);
    }
}
