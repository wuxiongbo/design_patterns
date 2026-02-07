package refactoring.chapter01.ver09;

import java.util.ArrayList;
import java.util.List;

/**
 * 在{@link Movie}类中，加入{@link Price}这一层间接性，我们就可以在 Price对象 内，进行子类化动作，(见uml 图) 于是，便可在任何必要时刻，修改价格。
 * <p>
 * 如果，你很熟悉 GoF(Gang of Four，四巨头) 所列的 各种 设计模式，可能会问：“这是一个 State,  还是一个 Strategy ? ”
 * 答案，取决于
 *   Price类 究竟 代表 计费方式(此时我喜欢把它叫做Pricer还PricingStrategy)，
 *           还是 代表 影片的某个状态(例如：“Star TrekX 是一部新片”)。
 * 在这个阶段，对于模式(和其名称)的选择，反映出你对结构的想法。
 * 此刻，我把它视为影片的某种状态。
 * 如果，未来我觉得 Strategy 能更好地说明我的意图，我会再重构它，修改名字，以形成 Strategy。
 * <p>
 * 为了引入State模式，我使用三个重构手法：
 * 首先，运用 Replace Type Code with State/Strategy(227)， 将 与 类型 相关的行为，搬移至 State模式 内。
 * 然后，运用 Move Method(142)                          ， 将 switch 语句移到 Price类。
 * 最后，运用 Replace Conditional with Polymorphism(255) ，去掉 switch语句。
 * <p>
 * 重构步骤：
 * 1)首先，我要使用 Replace Type Code with State/Strategy (227)。
 * 1.1 第一步骤，是针对 类型代码 (如：{@link Movie#REGULAR}) 使用 Self Encapsulate Field(171)，确保任何时候，都通过 取值函数 和 设值函数 来访问 类型代码。
 * 类型代码 的 多数 访问操作 来自 其他类，它们（{@link RegularPrice#getPriceCode()} 等子类）已经在使用 取值函数，
 * 但，构造函数{@link Movie#Movie(String, int)}，仍然 直接访问 价格代码（ _priceCode ），我可以用 设值函数{@link Movie#setPriceCode(int)} 来代替
 * 然后，编译并测试，确保没有破坏任何东⻄。
 * 1.2 现在，我新建⼀个Price类，并在其中提供 类型相关的⾏为。
 * 为了实现这⼀点，我在Price类内加⼊⼀个抽象函数，并在所有⼦类中加上对应的函数实现
 * 1.3 现在，我需要修改 Movie类内的 “价格代号”的访问函数（取值函数/设值函数），让它们使⽤新类({@link Price})。
 * 这意味着，我必须在Movie类内保存⼀个Price对象，⽽不再是保存⼀个 _priceCode 变量。
 * 现在，我可以重新编译并测试，那些⽐较复杂的函数根本不知道世界已经变了个样⼉。
 * 1.4 现在，我要对{@link refactoring.chapter01.ver08.Movie#getCharge(int)} 实施 Move Method（142）
 *     重构后{@link Movie#getCharge(int)}
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