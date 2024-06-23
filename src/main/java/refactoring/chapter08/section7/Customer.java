package refactoring.chapter08.section7;

import java.util.HashSet;
import java.util.Set;

/**
 * @author bear
 * @date 2024/6/16 下午3:17
 * @description
 */
public class Customer {

    private final Set<Order> _orders = new HashSet<>();

    public void removeOrder(Order order) {
        _orders.remove(order);
    }

    public void addOrder(Order order) {
        _orders.add(order);
    }

}
