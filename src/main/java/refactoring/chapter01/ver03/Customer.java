package refactoring.chapter01.ver03;

import lombok.Getter;

import java.util.*;

/**
 * 修改一，函数转移：<br>
 * 现在，我可以测试 转移后的 新函数 {@link Rental#getCharge()} 是否正常工作。<br>
 * 想要进行测试，只要改变 {@link Customer#amountFor(Rental)}，使它委托调用新函数{@link Rental#getCharge()} 即可：<br>
 * 1）现在，我可以编译并测试，看看有没有破坏什么东西。<br>
 * 2）下一个步骤是，找出程序中对于旧函数的所有引用点，并修改它们，让它们改用新函数。<br>
 * <p>
 * 本例之中，这个步骤很简单，因为我才刚刚产生新函数，只有一个地方使用了它。<br>
 * 一般情况下，你得在可能运用该函数的所有类中查找一遍。
 * <p>
 * 做完这些修改之后，下一件事就是——去掉旧函数。
 * 编译器会告诉我是否我漏掉了什么。然后，我进行测试，看看有没有破坏什么东西。
 * 有时候，我会保留 旧函数，让它调用 新函数。
 * 如果，旧函数 是一个 public函数，而我又不想修改其他类的接口，这便是一种有用的手法。
 * <p>
 * <p>
 * 修改二，去除多余变量：
 * 去掉 多余的 临时变量 thisAmount
 * 去掉前：
 * <pre>{@code
 *     public final double amountFor1(Rental each) {
 *         double thisAmount = 0;
 *         switch (each.getMovie().getPriceCode()) {
 *             case Movie.REGULAR:
 *                 thisAmount += 2;
 *                 if (each.getDaysRented() > 2) {
 *                     thisAmount += (each.getDaysRented() - 2) * 1.5;
 *                 }
 *                 break;
 *             case Movie.NEW_RELEASE:
 *                 thisAmount += each.getDaysRented() * 3;
 *                 break;
 *             case Movie.CHILDRENS:
 *                 thisAmount += 1.5;
 *                 if (each.getDaysRented() > 3) {
 *                     thisAmount += (each.getDaysRented() - 3) * 1.5;
 *                 }
 *                 break;
 *             default:
 *                 break;
 *         }
 *         return thisAmount;
 *     }
 * }</pre>
 *
 * <p>
 * 做完这份修改，我立刻编译并测试，保证自己没有破坏任何东西。
 * <p>
 * 我喜欢 尽量除去 这一类临时变量。
 * 临时变量，往往引发问题，它们会导致，大量 参数 被 传来传去，然而，实际上，其实完全没有这种必要。
 * 你很容易跟丢它们，尤其在长长的函数之中，更是如此。
 * 当然，我这么做也需付出性能上的代价，例如，本例的 费用 就被计算了两次。
 * 但是，这很容易在 Rental类 中被优化。而且，如果代码有合理地组织和管理，优化就会有很好的效果。
 * 我将在第69页的“重构与性能”一节详谈这个问题。
 * <p>
 * 下一步，要对“常客积分计算” frequentRenterPoints 变量 做类似处理。
 * “积分的计算” ，视 影片种类 而有所不同，不过，不像收费规则有那么多变化。
 * 看来，似乎有理由把 “积分计算” 的责任，放在 `Rental类` 身上。
 * 首先，需要针对 “常客积分计算” 这部分代码运用 Extract Method(110) 重构手法 :
 * 修改前：
 * <pre>{@code
 *
 *   frequentRenterPoints++;
 *   // add bonus for a two day new release rental
 *   if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
 *          && rental.getDaysRented() > 1) {
 *      frequentRenterPoints++;
 *   }
 *
 * }</pre>
 * <p>
 * <p>
 * 修改后：<br>
 * {@link refactoring.chapter01.ver04.Rental#getFrequentRenterPoints()}
 */
public class Customer {
    @Getter
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

        // 总消费金。
        double totalAmount = 0;

        // 常客积分
        int frequentRenterPoints = 0;

        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental rental : rentals) {
            // add frequent renter points
            // 累计常客积分
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    && rental.getDaysRented() > 1
            ) {
                frequentRenterPoints++;
            }
            // show figures for this rental（显示此笔租借记录）
            result.append("\t")
                    .append(rental.getMovie().getTitle())
                    .append("\t")
                    .append(rental.getCharge())
                    .append("\n");

            totalAmount += rental.getCharge();
        }

        // add footer lines（结尾打印）
        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");

        return result.toString();
    }

    /**
     * 委托调用（删除）
     * amountFor 函数 已经被 Rental.getCharge() 取代。
     *
     * @param aRental
     * @return
     */
    private double amountFor(Rental aRental) {
        return aRental.getCharge();
    }

}
