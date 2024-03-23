package my_demo.bridg.v2.coffee;

import my_demo.bridg.v2.additives.ICoffeeAdditives;

/**
 * 我们可以看到，Coffee 持有了 ICoffeeAdditives 引用，
 * ICoffeeAdditives 的实例是通过构造函数注入的，这个过程，就是我们所说的 "桥接过程"。
 * 这里的  ICoffeeAdditives 类，就是 设计的 "桥"
 * <p>
 * <p>
 * 我们通过这个引用，就可以调用 ICoffeeAdditives 的方法，
 * 进而，子类实现 orderCoffee() ， 将  ICoffee 的行为 与  ICoffeeAdditives 的行为， 组合起来。
 *
 * @author Xander Wu
 * @date 2022/9/27 18:00
 */
public abstract class Coffee implements ICoffee {

    protected ICoffeeAdditives additives;

    public Coffee(ICoffeeAdditives additives) {
        this.additives = additives;
    }


}
