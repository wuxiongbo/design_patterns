package my_demo.bridg.v1;

/**
 * 加糖咖啡
 * @author Xander Wu
 * @date 2022/9/27 17:56
 */
public class CoffeeWithSugar implements ICoffee {
    @Override
    public void orderCoffee(int count) {
        System.out.println(String.format("加糖咖啡%d杯",count));
    }
}
