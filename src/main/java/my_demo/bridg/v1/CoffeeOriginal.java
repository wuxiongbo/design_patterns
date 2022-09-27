package my_demo.bridg.v1;

/**
 * 原味咖啡
 * @author Xander Wu
 * @date 2022/9/27 17:56
 */
public class CoffeeOriginal implements ICoffee {
    @Override
    public void orderCoffee(int count) {
        System.out.println(String.format("原味咖啡%d杯",count));
    }
}
