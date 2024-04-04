package refactoring.chapter08.section3.v2;

import java.util.Collection;

/**
 * 将值对象改为引⽤对象
 * 将彼此相等的实例替换为同一个对象。
 * 这就要用一个工厂来创建这种唯一对象，工厂类中需要保留一份已经创建对象的列表，
 * 当要创建一个对象时，先查找这份列表中是否已经存在该对象，如果存在，则返回列表中的这个对象；否则，新建一个对象，添加到列表中，并返回该对象。
 *
 * <p>
 * 到⽬前为⽌，Customer对象还是值对象。
 * 就算多份定单属于同⼀客户，但每个order对象还是拥有各⾃的customer对象。
 * 我希望改变这⼀现状，使得⼀旦同⼀客户拥有多份不同定单，代表这些定单的所有order对象就可以共享同⼀个Customer对象。
 * 本例中，这就意味着：每⼀个客户名称只该对应⼀个Customer对象。
 *
 * @author bear
 * @date 2024/4/4 21:10
 * @description
 */
public class Order {
    private Customer _customer;

    public Order(String customer) {
        _customer = Customer.getNamed(customer);
    }

    public String getCustomerName() {
        return _customer.getName();
    }

    public void setCustomer(String customerName) {
        _customer = Customer.getNamed(customerName);
    }


    private static int numberOfOrdersFor(Collection<Order> orders, String customerName) {
        int result = 0;
        for (Order each : orders) {
            if (each.getCustomerName().equals(customerName)) {
                result++;
            }

        }
        return result;
    }

}

