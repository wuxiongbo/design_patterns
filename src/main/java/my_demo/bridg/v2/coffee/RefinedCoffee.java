package my_demo.bridg.v2.coffee;

import my_demo.bridg.v2.additives.ICoffeeAdditives;

import java.util.Random;

/**
 * 精调咖啡
 *
 * @author Xander Wu
 * @date 2022/9/27 18:01
 */
public abstract class RefinedCoffee extends Coffee {

    public RefinedCoffee(ICoffeeAdditives additives) {
        super(additives);
    }


    // 精致咖啡，增加 质量品控方法
    public void checkQuality() {
        System.out.println("》》》》》》质量检测");

        Random ran = new Random();
        System.out.printf("%s 添加%s%n", additives.getClass().getSimpleName(), ran.nextBoolean() ? "太多" : "正常");

        System.out.println("《《《《《《质量检测完成");
    }

}