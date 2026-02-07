package refactoring.chapter01.ver10;

import java.util.ArrayList;
import java.util.List;

/**
 * 接着，我们将 Price中 {@link Price#getCharge(int)} 的逻辑, 分散到各个子类:
 * <p>
 * 2)首先，我要对{@link refactoring.chapter01.ver08.Movie#getCharge(int)} 实施 Move Method(142),
 *   将 Movie类中{@link refactoring.chapter01.ver08.Movie#getCharge(int)}逻辑, 搬移至 Price类。
 *   重构后，{@link Movie#getCharge(int)}
 *
 * 3)现在，我就可以开始运用 Replace Conditional with Polymorphism(255) 了
 *   我的做法是，一次取出一个 case 分支，在相应的 类建立一个覆盖函数。
 *   首先，从 RegularPrice 开始 {@link RegularPrice#getCharge(int)}
 *   接下来，是 ChildrensPrice，NewReleasePrice
 *   最后，处理完所有的 case分支 后，我就把 {@link refactoring.chapter01.ver10.price.Price#getCharge(int)} 声明为 abstract
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