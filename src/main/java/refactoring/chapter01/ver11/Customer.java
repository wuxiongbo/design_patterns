package refactoring.chapter01.ver11;


import refactoring.chapter01.ver11.price.Price;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 4) 运用 与3) 同样的手法，处理 {@link Rental#getFrequentRenterPoints()},
 * 将 {@link Movie#getFrequentRenterPoints(int) }逻辑 搬移至 {@link Price#getFrequentRenterPoints(int)}。
 * 但是这一次，我不把超类函数声明为 abstract。
 * 我只是为 新片类型 增加一个 覆写函数，并在超类内留下一个已定义的函数{@link Price#getFrequentRenterPoints(int)}，使它成为一种默认行为。
 * <p>
 * =============================
 * <p>
 * 引入 State模式 花了我不少力气，值得吗?
 * 这么做的收获是：如果我要修改任何与 价格 Price 有关的行为，或是添加新的定价标准，或是加入其他取决于价格的行为，程序的修改会容易得多。
 * 这个程序的其余部分，并不知道我运用了 State模式。
 * 对于我目前拥有的这么几个小量行为来说，任何 功能 或 特性上的修改 也许都不合算，
 * 但，如果在一个更复杂的系统中，有十多个与价格相关的函数，程序的修改难易度就会有很大的区别。
 * 以上所有修改, 都是小步骤进行，进度似乎太过缓慢，但是，我一次都没有打开过调试器，所以，整个过程实际上很快就过去了。
 * 我写本章文字所用的时间，远比修改那些代码的时间多得多。
 * <p>
 * 现在，我已经完成了第二个重要的重构行为。
 * 从此，修改影片分类结构，或是改变费用计算规则、改变常客积分计算规则，都容易多了。
 * 图1-16 和 图1-17 描述 State模式 对于价格信息所起的作用。
 * <p>
 * =======================
 * <p>
 * 这是一个简单的例子，但我希望它能让你对于 “重构怎么做” 有一点感觉。
 * 例中，我已经示范了数个重构手法，包括：
 * Extract Method(110)、
 * Move Method (142)、
 * Replace Conditional with Polymorphism (255) 、
 * Self Encapsulate Field (171)、
 * ReplaceType Code with State/Strategy(227)。
 * 所有这些重构行为，都使责任的分配更合理，代码的维护更轻松。
 * 重构后的程序风格，将迥异于过程化风格，后者也许是某些人习惯的风格。
 * 不过，一旦你习惯了这种重构后的风格，就很难再满足于结构化风格了。
 * <p>
 * 这个例子给我们最大的启发是重构的节奏：测试、小修改、测试、小修改、测试、小修改......
 * 正是这种节奏，让重构得以快速而安全地前进。
 * <p>
 * 如果你看懂了前面的例子，就应该已经理解重构是怎么回事了。
 * 现在，让我们了解一些 背景、原理 和 理论(好在不太多)。
 */
public class Customer {
    private final String _name; // 姓名
    private final List<Rental> _rentals = new ArrayList<>(); // 租借记

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String getName() {
        return _name;
    }

    @SuppressWarnings("DuplicatedCode")
    public String statement() {
        // add header lines
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        // 详单细目
        for (Rental rental : _rentals) {
            // show figures for this rental
            result.append("\t")
                    .append(rental.getMovie().getTitle()).append("\t")
                    .append(rental.getCharge()).append("\n");
        }

        // add footer lines
        result.append("Amount owed is ").append(getTotalCharge()).append("\n");
        result.append("You earned ").append(getTotalFrequentRenterPoints()).append(" frequent renter points");
        return result.toString();
    }

    public String htmlStatement() {
        // add header lines
        StringBuilder result = new StringBuilder("<H1>Rentals for <EM>" + getName() + "</EM></ H1><P>\n");

        // 详单细目
        for (Rental rental : _rentals) {
            // show figures for each rental
            result.append(rental.getMovie().getTitle()).append(": ").append(rental.getCharge()).append("<BR>\n");
        }

        // add footer lines
        result.append("<P>You owe <EM>").append(getTotalCharge()).append("</EM><P>\n");
        result.append("On this rental you earned <EM>").append(getTotalFrequentRenterPoints()).append("</EM> frequent renter points<P>");
        return result.toString();
    }

    // 译注：此即所谓 query method
    private int getTotalFrequentRenterPoints() {
        int result = 0;
        for (Rental rental : _rentals) {
            result += rental.getFrequentRenterPoints();
        }
        return result;
    }

    // 译注：此即所谓query method
    private double getTotalCharge() {
        double result = 0;
        for (Rental rental : _rentals) {
            result += rental.getCharge();
        }
        return result;
    }

}