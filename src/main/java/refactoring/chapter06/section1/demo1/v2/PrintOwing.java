package refactoring.chapter06.section1.demo1.v2;

import refactoring.chapter06.section1.dependence.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * 无局部变量
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

        System.out.println("name:" + name);
        System.out.println("amount" + outstanding);
    }

    private static void printBanner() {
        System.out.println("***********************************");
        System.out.println("***** Customer Owes *************");
        System.out.println("***********************************");
    }


}
