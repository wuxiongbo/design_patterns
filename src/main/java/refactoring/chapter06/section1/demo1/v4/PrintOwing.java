package refactoring.chapter06.section1.demo1.v4;

import refactoring.chapter06.section1.dependence.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果被提炼代码段对局部变量赋值，问题就变得复杂了。
 * 这⾥我们只讨论临时变量的问题。
 * 如果,你发现源函数的参数被赋值，应该⻢上使⽤Remove Assignments to Parameters （131）。
 * <p>
 * 被赋值的临时变量也分两种情况。
 * 1)较简单的情况是：这个变量只在被提炼代码段中使⽤。果真如此，你可以将这个临时变量的声明移到被提炼代码段中，然后⼀起提炼出去。
 * 2)另⼀种情况是：被提炼代码段之外的代码也使⽤这个变量。这⼜分为两种情况：
 *   2.1)如果这个变量在被提炼代码段之后未再被使⽤，你只需直接在⽬标函数中修改它就可以了；
 *   2.2)如果被提炼代码段之后的代码还使⽤了这个变量，你就需要让⽬标函数返回该变量改变后的值。
 *
 * 我以下列代码说明这⼏种不同情况：
 *
 * @author bear
 * @date 2024/2/3 21:46
 * @description
 */
public class PrintOwing {
    List<Order> orders = new ArrayList<>();
    String name = "";


    void printOwing() {

        printBanner();

        double outstanding = getOutstanding();

        printDetails(outstanding);
    }

    /**
     * double变量 outstanding 在被提炼代码段内外都被⽤到，所以, 必须让提炼出来的新函数返回它。
     */
    private double getOutstanding() {
        double result = 0.0;
        for (Order order : orders) {
            result += order.getAmount();
        }
        return result;
    }

    /**
     * 局部变量最简单的情况是：被提炼代码段只是读取这些变量的值，并不修改它们。
     * 这种情况下我可以简单地将它们当作参数传给⽬标函数。
     * 所以,我们可以将“打印详细信息”这⼀部分提炼为带⼀个参数的函数：
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
