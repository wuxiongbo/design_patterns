package refactoring.chapter01.ver09;

import java.util.ArrayList;
import java.util.List;

/**
 * 加入这一层间接性，我们就可以在Price对象内，进行子类化动作，(见uml 图) 于是，便可在任何必要时刻修改价格。
 * <p>
 * 如果你很熟悉GoF(Gang of Four，四巨头) 所列的各种模式，可能会问：“这是一个State,  还是一个Strategy?”
 * 答案，取决于Price类 究竟 代表 计费方式(此时我喜欢把它叫做Pricer还PricingStrategy)，还是代表 影片的某个状态(例如：“Star TrekX 是一部新片”)。
 * 在这个阶段，对于模式(和其名称)的选择，反映出你对结构的想法。
 * 此刻，我把它视为影片的某种状态。如果，未来我觉得Strategy能更好地说明我的意图，我会再重构它，修改名字，以形成Strategy。
 * <p>
 * 为了引入State模式，我使用三个重构手法：
 * 首先，运用 Replace Type Code with State/Strategy (227)，将与类型相关的行为，搬移至State模式内。
 * 然后，运用 Move Method (142)，将 switch 语句移到 Price类。
 * 最后，运用 Replace Conditional with Polymorphism (255) ，去掉 switch语句。
 * <p>
 * 重构步骤：
 * 1)首先，我要使用 Replace Type Code with State/Strategy (227)。
 * 第一步骤是针对 类型代码，使用 Self Encapsulate Field(171)，确保任何时候都通过 取值函数 和 设值函数 来访问 类型代码。
 * 多数访问操作来自其他类，它们已经在使用 取值函数{@link Movie#getPriceCode()}
 * 但构造函数，仍然直接访问价格代码，我可以用 设值函数{@link Movie#setPriceCode(int)} 来代替
 * <p>
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