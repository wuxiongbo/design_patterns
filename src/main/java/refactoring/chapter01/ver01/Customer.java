package refactoring.chapter01.ver01;

import lombok.Getter;

import java.util.*;

/**
 * 顾客
 * <p>
 * statement() 里面做的事情太多了
 * <p>
 * 存在的问题：
 * <p>
 * 在这个例子里，我们的用户希望对系统做一点修改。
 * <p>
 * 首先，他们希望以HTML格式输出详单，这样就可以直接在网页上显示，这非常符合时下的潮流。
 * 现在请你想一想，这个变化会带来什么影响？
 * 看看代码你就会发现，根本不可能 在打印HTML报表的函数中，复用目前statement()的任何行为。
 * 你唯一可以做的就是，编写一个全新的 htmlStatement(), 大量重复statement() 的行为。
 * 当然，现在做这个还不太费力，你可以把 statement() 复制一份，然后按需要修改就是了。
 * <p>
 * 但如果计费标准发生变化，又会如何?
 * 你必须同时修改 statement() 和 htmlStatement(), 并确保两处修改的一致性。
 * 当你后续还要再修改时，复制粘贴帶来的问题就浮现出来了。
 * 如果你编写的是一个永不需要修改的程序，那么剪剪贴贴就还好，但如果程序要保存很长时间，而且可能需要修改，复制粘贴行为就会造成潜在的威胁。
 * <p>
 * 现在，第二个变化来了：用户希望改变影片分类规则，但是还没有决定怎么改。
 * 他们设想了几种方案，这些方案都会影响顾客消费和常客积分点的计算方式。
 * 作为一个经验丰富的开发者，你可以肯定：不论用户提出什么方案，你唯一能够获得的保证就是，他们一定会在六个月之内再次修改它。
 * <p>
 * 为了应付分类规则和计费规则的变化，程序必须对statement()做出修改。
 * 但如果，我们把statement()内的代码 复制到，用以打印HTML详单的函数中，就必须确保将来的任何修改在两个地方保持一致。
 * 随着各种规则变得愈来愈复杂，适当的修改点愈来愈难找，不犯错的机会也愈来愈少。
 * <p>
 * 建议：
 * 如果你发现，自己需要为程序添加一个特性，而代码结构使你无法很方便地达成目的，
 * 那么，就，首先，重构那个程序，使特性的添加比较容易进行，
 * 然后，再添加特性。
 * <p>
 * ====================
 * 重构的第一步：
 * <p>
 * 每当我要进行重构的时候，第一个步骤永远相同————我得为即将修改的代码建 立一组可靠的测试环境。
 * 这些测试是必要的，因为，尽管遵循重构手法可以使我避免绝大多数引入bug的情形，但我毕竟是人，毕竟有可能犯错。
 * 所以，我需要可靠的测试。
 * <p>
 * 测试过程中很重要的一部分，就是测试程序对于结果的报告方式。
 * 它们要么说 “OK”, 表示所有新字符串都和参考字符串一样，要么就列出失败清单，显示问题字符串的出现行号。这些测试都能够自我检验。
 * 是的，你必须让测试有能力自我检验，否则，就得耗费大把时间 来回比对，这会降低你的开发速度。
 * 进行重构的时候，我们需要依赖测试，让它告诉我们是否引入了bug。
 * 好的测试是重构的根本。花时间建立一个优良的测试机制是完全值得的，因为当你修改程序时，好测试会给你必要的安全保障。
 * 测试机制在重构领域的地位实在太重要了，我将在第4章详细讨论它。
 * <p>
 * 建议：
 * 重构之前，首先检查自己是否有一套可靠的测试机制。
 * 这些测试必须有自我检验能力。
 * <p>
 * ====================
 * 本章重构过程的第一阶段中，我将说明如何把长长的函数切开，并把较小块的代码移至更合适的类。
 * <p>
 * 我希望降低代码重复量，从而使新的(打印HTML详单用的)函数更容易撰写。
 * <p>
 * 第一个步骤是：找出代码的逻辑泥团，并运用 Extract Method (110)。
 * 本例一个明显的逻辑泥团就是switch 语句，把它提炼到独立函数中似乎比较好。
 * <p>
 * 和任何重构手法一样，当我提炼一个函数时，我必须知道可能出什么错。如果提炼得不好，就可能给程序引入bug。
 * 所以，重构之前，我需要先想出安全做法。
 * 由于，先前我已经进行过数次这类重构，所以，我已经把安全步骤记录于后面的重构列表中了。
 * <p>
 * 首先，我得在这段代码里找出函数内的局部变量和参数。{@link Customer#statement()}
 * 我找到了两个：each 和 thisAmount，前者并未被修改，后者会被修改。
 * 1）任何不会被修改的变量，都可以被我当成 参数 传入新的函数，
 * 2）至于会被修改的变量，就需格外小心。
 * 如果，只有一个变量会被修改，我可以把它当作返回值。
 * 由于 thisAmount 是个临时变量，其值在每次循环起始处被设为0，并且 在switch 语句之前不会改变，
 * 所以，我可以直接把新函数的返回值赋给它。
 *
 * @author bear
 */
public class Customer {
    /**
     * 姓名
     */
    @Getter
    private final String name;
    // 租借记录
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    /**
     * 结算单
     *
     * @return 结算单详情
     */
    public String statement() {
        double totalAmount = 0;
        // 总消费金。
        int frequentRenterPoints = 0;
        // 常客积点

        Iterator<Rental> rentals = this.rentals.iterator();
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        while (rentals.hasNext()) {
            // 当前金额
            double thisAmount = 0;
            // 取得租借记录
            Rental each = rentals.next();

            // determine amounts for each line
            // 取得影片出租价格
            switch (each.getMovie().getPriceCode()) {

                // 普通片
                case Movie.REGULAR -> {
                    thisAmount += 2;
                    if (each.getDaysRented() > 2) {
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    }
                }

                // 新片
                case Movie.NEW_RELEASE -> thisAmount += each.getDaysRented() * 3;

                // 儿童
                case Movie.CHILDREN -> {
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3)
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                }
                default -> {
                }

            }

            // add frequent renter points
            // 添加常租积分
            frequentRenterPoints++;

            // add bonus for a two day new release rental
            // 租期超过两天的新片，额外添加租赁奖励
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    && each.getDaysRented() > 1)
                frequentRenterPoints++;


            // show figures for this rental
            // 显示此笔租借记录
            result.append("\t").append(each.getMovie().getTitle()).append("\t")
                    .append(thisAmount).append("\n");

            totalAmount += thisAmount;
        }

        // add footer lines
        // 结尾打印
        result.append("Amount owed is ")
                .append(totalAmount).append("\n");
        result.append("You earned ")
                .append(frequentRenterPoints)
                .append(" frequent renter points");

        return result.toString();
    }
}
