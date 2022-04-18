package my_demo.adapter;

/**
 * <p> 接口适配器模式 / 缺省适配器模式  </p>
 * 当不需要实现接口提供的所有方法时，可以
 *      先设计一个实现了该接口的抽象类，
 *      并且，在抽象类中为该接口中的每一个方法提供一个 “模式实现”（空方法），
 *
 * 这样，该抽象类的子类，就可有选择的覆盖父类的某些方法来实现需求。而不需要将接口全部都实现一遍。
 *
 * 适用于一个接口不想使用其所有的方法的情况
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Client {
    public static void main(String[] args) {

        Charger5V charger = new Charger5V();

        System.out.println("使用"+charger.Voltage5V()+"V充电器，开始充电！！！");

    }
}
