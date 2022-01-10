package my_demo.command.demo1.invoker;

import my_demo.command.demo1.command.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Invoker</p>
 *
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class Broker {

    // 可以是 List，可以是一个 Order引用。 总之，就是 聚合关系
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}