package refactoring.chapter01.ver08;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * 修改前：{@link refactoring.chapter01.ver07.Rental#getCharge()}
 * 修改后：{@link Rental#getCharge()}
 * <p>
 *
 * 终于⋯我们来到继承
 * <p>
 * 我们有数种影片类型，它们以不同的方式回答相同的问题。
 * 这听起来很像子类的工作。
 * 我们可以建立Movie 的三个子类，每个都有自己的计费法。(图：movie.puml)
 *
 * 这么⼀来，我就可以⽤ 多态 来取代 switch语句 了。
 * 很遗憾的是这⾥有个⼩问题，不能这么⼲ ———— ⼀部影⽚可以在⽣命周期内修改⾃⼰的分类，⼀个对象却不能在⽣命周期内修改⾃⼰所属的类。
 * 不过，还是有⼀个解决⽅法：State模式［Gang ofFour］。
 * 运⽤它之后，我们的类看起来像图1-15。（State.puml）
 * <p>
 * <p>以下描述，是对“⼀部影⽚可以在⽣命周期内修改⾃⼰的分类，⼀个对象却不能在⽣命周期内修改⾃⼰所属的类。” 这句话的解读
 * 新需求：用户准备修改影片分类规则
 * <p>
 * (系统运行前)一部影片(Movie类)可以在生命周期内修改自己的分类(修改代码)，
 * (系统运行后)一个对象(Movie实例)却不能在生命周期内修改自己所属的类。
 * <p>
 * 比如：
 * Movie newReleaseMovie = new Movie("饥饿游戏3",Movie.NEW_RELEASE);
 * newReleaseMovie 的 type 是 NEW_RELEASE
 * newReleaseMovie 的 name 是 "饥饿游戏3";
 * <p>
 * Movie对象 创建之后，任何时候调用 {@link Movie#getCharge(int)} 都是调用 该 Movie实例 的 getCharge()方法。
 * 但是，上映一段时间之后，影片的 type 改成了 REGULAR_MOVIE 了，（也就是对前半句话的解释）
 * 但是，影片还是那个影片，即————实例化的对象没有变。
 * 实例化的对象没有变，这就意味着 NewReleaseMovie 的 getCharge() 方法不变，getCharge() 中，没有针对 影片类型为 REGULAR_MOVIE 的计算逻辑，这样就出现了问题了。
 *
 * <p>
 * 这里的问题更具体的描述是：
 * 正在运行的 Movie 实例对象，不能修改 自己所属 Movie类 的 getCharge() 方法。
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

    /**
     * 重现写一个 html账单函数，实现过程中可以复用原statement()中的所有计算
     * 非常轻松的就实现了 一个 htmlStatement()
     *
     * @return 账单
     */
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