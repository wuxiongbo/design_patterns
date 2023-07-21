package my_demo.bridg.v1.impl;

import my_demo.bridg.v1.ICoffee;

/**
 * 大杯加糖
 * @author Xander Wu
 * @date 2022/9/27 17:58
 */
public class LargeCoffeeWithSugar implements ICoffee {
    @Override
    public void orderCoffee(int count) {
        System.out.printf("大杯加糖咖啡%d杯%n",count);
    }
}
