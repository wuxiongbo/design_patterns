package refactoring.chapter06.section1.demo1.v5;

import refactoring.chapter06.section1.dependence.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * 这时候，你可能会问：“如果需要返回的变量不⽌⼀个，⼜该怎么办呢？”
 * 有⼏种选择。
 * 最好的选择通常是：
 * 挑选另⼀块代码来提炼。
 * 我⽐较喜欢让每个 函数都只返回⼀个值，所以会安排多个函数，⽤以返回多个值。
 * 如果你使⽤的语⾔⽀持“出参数”（output parameter），可以使⽤它们带回多个回传值。
 * 但我还是尽可能选择单⼀返回值。
 *
 * <p>
 * 临时变量往往为数众多，甚⾄会使提炼⼯作举步维艰。
 * 这种情况下，我会尝试先运⽤Replace Temp with Query （120）减少临时变量。
 * 如果,即使这么做了,提炼依旧困难重重，我就会动⽤Replace Method with Method Object（135），
 * 这个重构⼿法不在乎代码中有多少临时变量，也不在乎你如何使⽤它们。
 *
 * @author bear
 * @date 2024/2/3 21:46
 * @description
 */
public class PrintOwing {
    List<Order> orders = new ArrayList<>();
    String name = "";

    void printOwing(double previousAmount) {
        printBanner();
        double outstanding = getOutstanding(previousAmount * 1.2);
        printDetails(outstanding);
    }

    /**
     * 如果代码还对这个变量做了其他处理，就必须将它的值作为参数传给⽬标函数。
     */
    private double getOutstanding(double initialValue) {
        double result = initialValue;
        for (Order order : orders) {
            result += order.getAmount();
        }
        return result;
    }

    /**
     * 局部变量最简单的情况是：被提炼代码段只是读取这些变量的值，并不修改它们。
     * 这种情况下, 我可以简单地将它们当作参数传给⽬标函数。
     * 所以, 我们可以将“打印详细信息”这⼀部分提炼为带⼀个参数的函数：
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
