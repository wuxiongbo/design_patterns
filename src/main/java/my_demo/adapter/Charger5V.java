package my_demo.adapter;

/**
 * @Author: Battle Bear
 * @Date: 2022/4/16 12:22
 * @Description:
 */
public class Charger5V extends ChargerAdapter{
    @Override
    public int Voltage5V() {
        System.out.println("充电器输出5V电流");
        return 5;
    }

    // 其他方法都有默认实现。
    // 所以，这里可以不再强制实现。

}
