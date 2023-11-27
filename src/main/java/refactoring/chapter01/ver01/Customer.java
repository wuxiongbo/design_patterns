package refactoring.chapter01.ver01;

import java.util.*;

/**
 * 顾客
 *
 */
public class Customer {
    private final String _name; // 姓名
    private final List<Rental> _rentals = new ArrayList<>(); // 租借记录

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String getName() {
        return _name;
    }

    /**
     * 结算单
     *
     * @return 结算单详情
     */
    public String statement() {
        double totalAmount = 0; // 总消费金。
        int frequentRenterPoints = 0; // 常客积点

        Iterator<Rental> rentals = _rentals.iterator();
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
                    if (each.getDaysRented() > 2)
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                }

                // 新片
                case Movie.NEW_RELEASE ->
                        thisAmount += each.getDaysRented() * 3;

                // 儿童
                case Movie.CHILDREN -> {
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3)
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
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
