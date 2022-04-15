package my_demo.adapter;

/**
 * <p> 接口适配器模式 / 缺省适配器模式  </p>
 * 当不需要实现接口提供的所有方法时，可以先设计一个抽象类实现接口，
 *   并为该接口中的每一个方法提供一个 “模式实现”（空方法），那么，该抽象类的子类，则可有选择的覆盖父类的某些方法来实现需求。
 *
 * 适用于一个接口不想使用其所有的方法的情况
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/15
 * </pre>
 */
public class Client {
    public static void main(String[] args) {

        ChargerAdapter charger = new ChargerAdapter() {
            @Override
            public int Voltage5V() {
                return 5;
            }
        };

        System.out.println("用"+charger.Voltage5V()+"V开始充电！！！");
    }
}
