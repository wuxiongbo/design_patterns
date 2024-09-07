package refactoring.chapter01.ver07;

import refactoring.chapter01.ver08.Movie;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * 通过计算逻辑的提炼，我可以完成一个htmlStatement()，并复用原本statement() 内的所有计算。
 * 我不必剪剪贴贴，所以如果计算规则发生改变，我只需在程序中做一处修改。
 * 完成其他任何类型的详单也都很快而且很容易。
 * 这次重构并没有花很多时间，其中大半时间我用来弄清楚代码所做的事，而这是我无论如何都得做的。
 *
 * 前面有些代码是从 ASCI版本中复制过来的，主要是循环设置部分。
 * 更深入的重构动作可以清除这些重复代码。
 * 我可以把处理 表头 (header)、 表尾 (footer) 和 详单细目的代码，都分别提炼出来。
 * 在 Form Template Method (345)实例中，你可以看到如何做这些动作。
 *
 * 但是，现在用户又开始嘀咕了，他们准备修改 影片分类规则。
 * 我们尚未清楚他们想怎么做，但似乎新分类法很快就要引入，现有的 分类方法 马上就要变更。
 * 与之相应的 费用计算方式 Rental.getCharge() 和 常客积分计算方式 Rental.getFrequentRenterPoints() 都还有待决定，现在就对程序做修改，肯定是愚蠢的。
 * 我必须进入 费用计算 和 常客积分计算 中，把因条件而异的代码（switch 内的 case语句）{@link Rental#getCharge()} 替换掉，这样，才能为将来的改变镀上一层保护膜。(防腐？)
 * 现在，请重新戴回“重构”这顶帽子。
 *
 * 这个问题的第一部分是 getCharge 中的 switch 语句。{@link Rental#getCharge()}
 * 最好不要在 另一个对象的属性基础上，运用 switch语句。
 * 如果不得不使用，也应该在 对象自己的数据上 使用，而不是在 别人的数据上 使用。
 * 这暗示着 Rental类的 getCharge() 应该移到 Movie类 里去：
 *
 * 分析思路：
 * 为了让它得以运作，我必须把租期长度作为参数传递进去。当然，租期长度来自 Rental对象。
 * 计算费用时，需要两项数据：租期长度 和 影片类型。
 * 为什么我选择将 租期长度 传给 Movie 对象，而不是将 影片类型 传给 Rental 对象呢?
 * 因为，本系统可能发生的变化是 加入新的 影片类型，这种变化带有不稳定倾向。
 * 如果影片类型有所变化，我希望尽量控制它造成的影响，所以，选择在 Movie 对象 内计算费用。
 *
 * 修改步骤：
 * 1）把 上述计费方法 从 Rental 迁移，放进 Movie 类 {@link Movie#getCharge(int)}
 * 2）然后，修改 Rental 类 的 getCharge()， 让它使用这个新函数。{@link refactoring.chapter01.ver08.Rental#getCharge()}
 * 3）以相同手法处理 常客积分计算 {@link Rental#getFrequentRenterPoints()}
 *    这样，我就把 根据影片类型而变化 的所有东西，都放到了 影片类型所属的类 中。{@link refactoring.chapter01.ver08.Rental#getFrequentRenterPoints()}
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
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        for (Rental rental : _rentals) {
            // show figures for this rental
            result.append("\t").append(rental.getMovie().getTitle())
                    .append("\t").append(rental.getCharge()).append("\n");
        }
        // add footer lines
        result.append("Amount owed is ")
                .append(getTotalCharge()).append("\n");
        result.append("You earned ")
                .append(getTotalFrequentRenterPoints())
                .append(" frequent renter points");
        return result.toString();
    }

    @SuppressWarnings("DuplicatedCode")
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

    // 译注：此即所谓query method
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