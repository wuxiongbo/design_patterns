package my_demo.bridg.v2.coffee;

import my_demo.bridg.v2.additives.ICoffeeAdditives;

import java.util.Random;

/**
 * 精致咖啡
 *
 * 我们可以看到，Coffee 持有了 ICoffeeAdditives 引用，
 * ICoffeeAdditives 的实例是通过构造函数注入的，这个过程，就是我们所说的 "桥接过程"。
 * 这里的  ICoffeeAdditives 类，就是 设计的 "桥"
 *
 *
 * 我们通过这个引用，就可以调用 ICoffeeAdditives 的方法，
 * 进而，子类实现 orderCoffee() ， 将  ICoffee 的行为 与  ICoffeeAdditives 的行为， 组合起来。
 *
 * @author Xander Wu
 * @date 2022/9/27 18:01
 */
public abstract class RefinedCoffee implements ICoffee {

    protected ICoffeeAdditives additives;

    public RefinedCoffee(ICoffeeAdditives additives) {
//        super(additives);
        this.additives = additives;
    }

    // 质量品控方法
    public void checkQuality() {
        Random ran = new Random();
        System.out.printf("%s 添加%s%n", additives.getClass().getSimpleName(), ran.nextBoolean() ? "太多" : "正常");
    }


}