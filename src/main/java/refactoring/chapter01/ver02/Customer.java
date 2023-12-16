package refactoring.chapter01.ver02;

import java.util.*;

/**
 * 第一次重构后：
 * 现在，我已经把原来的函数分为两块，可以分别处理它们。
 * 我不喜欢 amountFor()内的某些变量名称，现在正是修改它们的时候。{@link Customer#amountFor(Rental)}
 *
 * 更改变量名称是值得的行为吗?
 * 绝对值得。好的代码应该清楚表达出自己的功能，变量名称是代码清晰的关键。
 * 如果为了提高代码的清晰度，需要修改某些东西的名字，那么就大胆去做吧。只要有良好的查找/替换工具，更改名称并不困难。
 * 语言所提供的强类型检查以及你自己的测试机制，会指出任何你遗漏的东西。
 *
 * 记住：
 * 任何一个傻瓜都能写出计算机可以理解的代码。
 * 唯有写出人类容易理解的代码，才是优秀的程序员。
 *
 *
 * 代码应该表现自己的目的，这一点非常重要。
 * 阅读代码的时候，我经常进行重构。这样，随着对程序的理解逐渐加深，我也就不断地把这些理解嵌入代码中，这么一来，才不会遗忘我曾经理解的东西。
 *
 *
 * <p>
 * <p>
 * 重构步骤的本质：
 * 由于每次修改的幅度都很小，所以任何错误都很容易发现。
 * <p>
 * 建议：
 * 重构技术就是以微小的步伐修改程序。
 * 如果你犯下错误，很容易便可发现它。
 *
 *
 * 待修改点：
 * 1）观察 amountFor() 时，我发现这个函数使用了来自 Rental类 的信息，却没有使用来自 Customer 类的信息。
 * 这立刻使我怀疑它是否被放错了位置。
 * 绝大多数情况下，函数应该放在它所使用的数据的所属对象内，所以，amountFor() 应该移到Rental 类去。
 * 为了这么做，我要运用 Move Method(142)。
 * 首先，把代码复制到 Rental 类，调整代码使之适应新家，
 * 然后，重新编译。
 *
 * 在这个例子里，“适应新家”意味着要去掉参数。
 * 此外，我还要在搬移的同时变更函数名称。
 *
 * 2）下一件引我注意的事是：thisAmount 如今变得多余了。
 * 它接受 each.getCharge()的执行结果，然后就不再有任何改变。
 * 所以，我可以运用 Replace Temp with Query(120) 把 thisAmount 除去
 *
 * 3）下一步要对“常客积分计算”做类似处理。
 * 积分的计算，视 影片种类 而有所不同，不过，不像收费规则有那么多变化。
 * 看来，似乎有理由把积分计算责任放在Rental类 身上。
 * 首先，需要针对 “常客积分计算” 这部分代码，
 * <pre>
 * {@code
 *   frequentRenterPoints++;
 *   // add bonus for a two day new release rental
 *   if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
 *          && rental.getDaysRented() > 1) {
 *      frequentRenterPoints++;
 *   }
 * }
 * </pre>
 * 运用 Extract Method(110) 重构手法,
 *
 *
 */
public class Customer {
    private final String _name; // 姓名
    private final List<Rental> _rentals = new ArrayList<>(); // 租借记录

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmount = 0; // 总消费金。
        int frequentRenterPoints = 0; // 常客积点

        Iterator<Rental> rentals = _rentals.iterator();
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        while (rentals.hasNext()) {
            // 取得一笔租借记。
            Rental each = rentals.next();

            double thisAmount = amountFor(each);

            // add frequent renter points
            // 累计常客积点。
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    && each.getDaysRented() > 1)
                frequentRenterPoints++;

            // show figures for this rental
            // 显示此笔租借记录
            result.append("\t")
                    .append(each.getMovie().getTitle()).append("\t")
                    .append(thisAmount).append("\n");

            totalAmount += thisAmount;
        }

        // add footer lines（结尾打印）
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");

        return result.toString();
    }

    /**
     * 修改后
     * 根据租赁记录计算费用
     * <p>
     * 1）抽取方法
     * 2）修改变量名
     *
     * @param aRental 一个租赁实例
     * @return
     */
    private double amountFor(Rental aRental) {
        double result = 0;
        // determine amounts for each line
        switch (aRental.getMovie().getPriceCode()) { // 取得影片出租价格

            case Movie.REGULAR -> { // 普通片
                result += 2;
                if (aRental.getDaysRented() > 2)
                    result += (aRental.getDaysRented() - 2) * 1.5;
            }

            case Movie.NEW_RELEASE -> // 新片
                    result += aRental.getDaysRented() * 3;

            case Movie.CHILDRENS -> { // 儿童。
                result += 1.5;
                if (aRental.getDaysRented() > 3)
                    result += (aRental.getDaysRented() - 3) * 1.5;
            }
        }

        return result;
    }

    // 修改前（删除）
    public final double amountFor1(Rental each) {
        double thisAmount = 0;
        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (each.getDaysRented() > 3)
                    thisAmount += (each.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }
}
