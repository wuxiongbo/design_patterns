package refactoring.chapter08.section2.v1;

import java.util.Collection;

/**
 * 下⾯有⼀个代表“定单”的order类，其中以⼀个字符串记录定单客户。
 * 现在，我希望改⽤⼀个对象来表示客户信息，这样就有充裕的弹性保存客户地址、信⽤等级等信息，也得以安置这些信息的操作⾏为。
 * Order类最初如下：
 *
 * @author bear
 * @date 2024/4/4 21:10
 * @description
 */
public class Order {
    private String _customer;

    public Order(String customer) {
        _customer = customer;
    }

    public String getCustomer() {
        return _customer;
    }

    public void setCustomer(String arg) {
        _customer = arg;
    }


    private static int numberOfOrdersFor(Collection<Order> orders, String customer) {
        int result = 0;
        for (Order each : orders) {
            if (each.getCustomer().equals(customer)) {
                result++;
            }

        }
        return result;
    }

}

