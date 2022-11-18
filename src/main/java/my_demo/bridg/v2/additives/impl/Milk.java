package my_demo.bridg.v2.additives.impl;

import my_demo.bridg.v2.additives.ICoffeeAdditives;

/**
 * 添加剂 ———— 牛奶
 * @author Xander Wu
 * @date 2022/9/27 18:02
 */
public class Milk implements ICoffeeAdditives {
    @Override
    public void addSomething() {
        System.out.println("添加牛奶");
    }
}
