package refactoring.chapter01.ver03;

import java.util.*;

/**
 * 修改一，函数转移：
 * 现在，我可以测试转移后的新函数 Rental.getCharge() 是否正常工作。{@link Rental#getCharge()}
 * 想要进行测试，只要改变 Customer.amountFor() {@link Customer#amountFor1(Rental)} 内容，使它委托调用新函数 getCharge() 即可：
 * 1）现在，我可以编译并测试，看看有没有破坏什么东西。
 * 2）下一个步骤是，找出程序中对于旧函数的所有引用点，并修改它们，让它们改用新函数。
 * <p>
 * 本例之中，这个步骤很简单，因为我才刚刚产生新函数，只有一个地方使用了它。
 * 一般情况下，你得在可能运用该函数的所有类中查找一遍。
 * <p>
 * 做完这些修改之后，下一件事就是去掉旧函数。
 * 编译器会告诉我是否我漏掉了什么。然后我进行测试，看看有没有破坏什么东西。
 * 有时候，我会保留旧函数，让它调用新函数。
 * 如果，旧函数是一个public函数，而我又不想修改其他类的接口，这便是一种有用的手法。
 * <p>
 * 修改二，去除多余变量：
 * 去掉 多余的 临时变量 thisAmount
 * 去掉前：{@link refactoring.chapter01.ver02.Customer#amountFor1(refactoring.chapter01.ver02.Rental)}
 * <p>
 * 做完这份修改，我立刻编译并测试，保证自己没有破坏任何东西。
 * <p>
 * 我喜欢尽量除去这一类临时变量。
 * 临时变量往往引发问题，它们会导致大量参数被传来传去，而其实完全没有这种必要。
 * 你很容易跟丢它们，尤其在长长的函数之中更是如此。
 * 当然，我这么做也需付出性能上的代价，例如本例的费用就被计算了两次。
 * 但是这很容易在 Rental类 中被优化。而且，如果代码有合理的组织和管理，优化就会有很好的效果。
 * 我将在第69页的“重构与性能”一节详谈这个问题。
 * <p>
 * <p>
 * <p>
 * 下一步，要对“常客积分计算” frequentRenterPoints 做类似处理。
 * 积分的计算，视 影片种类 而有所不同，不过，不像收费规则有那么多变化。
 * 看来，似乎有理由把 积分计算 的责任，放在 Rental类 身上。
 * 首先，需要针对 “常客积分计算” 这部分代码运用 Extract Method(110) 重构手法 :
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
 * 修改后：{@link refactoring.chapter01.ver04.Rental#getFrequentRenterPoints()}
 */
public class Customer {
    private final String name;
    // 姓名
    private final List<Rental> rentals = new ArrayList<>();
    // 租借记

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    /**
     * 至此，作了两处修改：
     * 1）修改函数引用点
     * double thisAmount = rental.getCharge();
     * 2）去掉多余的临时变量
     * thisAmount
     *
     * @return
     */
    public String statement() {
        double totalAmount = 0; // 总消费金。
        int frequentRenterPoints = 0; // 常客积分

        Iterator<Rental> rentals = this.rentals.iterator();
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        while (rentals.hasNext()) {

            Rental rental = rentals.next(); // 取得一笔租借记。

            // add frequent renter points
            // 累计常客积分
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    && rental.getDaysRented() > 1) {
                frequentRenterPoints++;
            }
            // show figures for this rental（显示此笔租借记录）
            result.append("\t").append(rental.getMovie().getTitle()).append("\t")
                    .append(rental.getCharge()).append("\n");
            totalAmount += rental.getCharge();
        }

        // add footer lines（结尾打印）
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");

        return result.toString();
    }

    // 委托调用（删除）
    private double amountFor1(Rental aRental) {
        return aRental.getCharge();
    }

}
