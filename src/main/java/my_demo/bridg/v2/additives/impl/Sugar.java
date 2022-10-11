package my_demo.bridg.v2.additives.impl;

import my_demo.bridg.v2.additives.ICoffeeAdditives;

/**
 * 添加剂 ———— 糖
 * @author Xander Wu
 * @date 2022/9/27 18:02
 */
public class Sugar implements ICoffeeAdditives {
    @Override
    public void addSomething() {
        System.out.println("加糖");
    }
}
