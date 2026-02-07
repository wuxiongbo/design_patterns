package refactoring.chapter01.ver07;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过计算逻辑的提炼，我可以完成一个 htmlStatement()，并复用原本 statement() 内的所有计算。
 * 我不必剪剪贴贴，所以，如果计算规则发生改变，我只需在程序中做一处修改。
 * 完成其他任何类型的详单也都很快而且很容易。
 * 这次重构并没有花很多时间，其中大半时间我用来弄清楚代码所做的事，而这是我无论如何都得做的。
 * <p>
 * {@link Customer#htmlStatement()} 中 有些代码是从 ASCII版本 {@link Customer#statement()}中 复制过来的，主要是循环设置部分。
 * 更深入的重构动作，可以清除这些重复代码。
 * 我可以把处理 表头 (header)、 表尾 (footer) 和 详单细目 的代码，都分别提炼出来。
 * 在 Form Template Method(345) 实例中，你可以看到如何做这些动作。
 * <p>
 *
 * 但是，现在用户又开始嘀咕了，他们准备修改 影片分类规则:
 * {@link refactoring.chapter01.ver07.Movie#REGULAR}
 * {@link refactoring.chapter01.ver07.Movie#NEW_RELEASE}
 * {@link refactoring.chapter01.ver07.Movie#CHILDRENS}
 * ;
 * 我们尚未清楚他们想怎么做，但似乎 新分类法 很快就要引入，现有的 分类方法 马上就要变更。
 * 与之相应的
 *    费用计算方式 {@link Rental#getCharge()}
 *    和
 *    常客积分计算方式 {@link Rental#getFrequentRenterPoints()}
 * 都还有待决定。
 * 基于当前的代码结构，现在就对程序做修改，肯定是愚蠢的。
 * 我必须进入 费用计算{@link Rental#getCharge()} 和 常客积分计算{@link Rental#getFrequentRenterPoints()} 中，
 * 把 {@link Rental#getCharge()} 中，因条件而异的代码（switch内的case语句）替换掉（条件中，调用 “谁” 的属性 进行判断，该代码 就迁移给 “谁”）
 * 这样，才能 为将来的改变，镀上一层 保护膜。 (防腐？)
 * 现在，请重新 戴回 “重构” 这顶帽子。
 * <p>
 *
 *
 * ⽤“多态”取代 与价格相关的条件逻辑
 * <p>
 * 这个问题的第一部分是——{@link Rental#getCharge()}中的 switch 语句。
 * 最好不要在 另一个对象 的属性 基础上，运用switch语句。
 * 如果，switch语句 不得不使用 另一个对象的属性，那么，也应该在 对象 自己的数据上使用，而不是在 别人的数据上使用。
 * 这暗示着，Rental类 的{@link Rental#getCharge()} 应该移到 Movie类 里去：
 * <p>
 * 分析思路：
 * 为了让它得以运作，我必须把 租期长度{@link Rental#getDaysRented()} 作为参数，传递进去 给Movie.getCharge()。
 * 当然，租期长度 daysRented 来自 Rental对象。
 * 计算费用时，需要两项数据：
 *    租期长度 {@link Rental#getDaysRented()}
 *    和
 *    影片类型 {@link refactoring.chapter01.ver07.Movie#getPriceCode()}。
 * 为什么我选择 将 Rental 的 租期长度 传给 Movie，而不是将 Movie 的 影片类型 传给 Rental 呢?
 * 因为，本系统可能发生的变化是 加入新的影片类型，这种变化带有不稳定倾向。
 * 如果，影片类型 有所变化，我希望尽量 控制它造成的影响，所以，选择在 Movie对象 内 计算费用。
 * <p>
 * 修改步骤：
 * 1）把 上述 计费方法：费用计算方式 {@link Rental#getCharge()} 和 常客积分计算方式 {@link Rental#getFrequentRenterPoints()} 从 Rental 迁移;
 *    首先，把{@link Rental#getCharge()}中的逻辑 迁移到 Movie类 的{@link Movie#getCharge(int)} 函数中
 * 2）然后，修改 Rental类 的 {@link Rental#getCharge()}，让它委托{@link Movie#getCharge(int)}这个新函数。
 *    改后效果{@link refactoring.chapter01.ver08.Rental#getCharge()}
 * 3）以相同手法处理 常客积分计算 {@link Rental#getFrequentRenterPoints()}。
 *    改后效果{@link refactoring.chapter01.ver08.Rental#getFrequentRenterPoints()}
 *
 * 这样，我就把 依据 影片类型 而变化 的 所有东西，都放到了 影片类型 所属的类 中。
 *
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
            result.append("\t")
                    .append(rental.getMovie().getTitle())
                    .append("\t")
                    .append(rental.getCharge())
                    .append("\n");
        }

        // add footer lines
        result.append("Amount owed is ")
                .append(getTotalCharge())
                .append("\n");

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
            result.append(rental.getMovie().getTitle())
                    .append(": ")
                    .append(rental.getCharge())
                    .append("<BR>\n");
        }

        // add footer lines
        result.append("<P>You owe <EM>")
                .append(getTotalCharge())
                .append("</EM><P>\n");

        result.append("On this rental you earned <EM>")
                .append(getTotalFrequentRenterPoints())
                .append("</EM> frequent renter points<P>");

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