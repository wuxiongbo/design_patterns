package refactoring.chapter06.section1.demo1.v3;

import refactoring.chapter06.section1.dependence.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * 有局部变量
 * @author bear
 * @date 2024/2/3 21:46
 * @description
 */
public class PrintOwing {
    List<Order> orders = new ArrayList<>();
    String name = "";


    void printOwing() {
        double outstanding = 0.0;

        printBanner();

        for (Order order : orders) {
            outstanding += order.getAmount();
        }

        printDetails(outstanding);
    }

    /**
     * 局部变量最简单的情况是：被提炼代码段只是读取这些变量的值，并不修改它们。
     * 这种情况下我可以简单地将它们当作参数传给⽬标函数。
     * 所以,我们可以将“打印详细信息”这⼀部分提炼为带⼀个参数的函数：
     * @param outstanding
     */
    private void printDetails(double outstanding) {
        System.out.println("name:" + name);
        System.out.println("amount" + outstanding);
    }

    private static void printBanner() {
        System.out.println("***********************************");
        System.out.println("***** Customer Owes *************");
        System.out.println("***********************************");
    }


}
