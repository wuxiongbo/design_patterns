package refactoring.chapter08.section2.v2;

import java.util.Collection;

/**
 * 本次重构到此为⽌。但是，这个案例和其他很多案例⼀样，还需要⼀个后续步骤。
 * 如果想在customer 中加⼊信⽤等级、地址之类的其他信息，现在还做不到，
 * 因为,⽬前的customer还是作为值对象对待的(多例)，每个order对象都拥有⾃⼰的customer对象。
 * 为了给Customer类加上信⽤等级、地址之类的属性，我必须运⽤Change Value to Reference （179），
 * 这么⼀来，属于同⼀客户的所有order对象就可以共享同⼀个 Customer 对象了。
 * ⻢上你就可以看到这个例⼦;
 *
 * @author bear
 * @date 2024/4/4 21:10
 * @description
 */
public class Order {
    private Customer _customer;

    public Order(String customer) {
        _customer = new Customer(customer);
    }

    public String getCustomerName() {
        return _customer.getName();
    }

    public void setCustomer(String customerName) {
        _customer = new Customer(customerName);
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

