package refactoring.chapter01.ver06;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * frequentRentalPoints 变量 改为查询函数后
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

    public String statement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        for (Rental rental : _rentals) {
            // show figures for this rental
            result.append("\t").append(rental.getMovie().getTitle())
                    .append("\t").append(rental.getCharge()).append("\n");
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
     * 用同样的手法，处理 frequentRenterPoints 变量 为查询函数
     *
     * @return 积分
     */
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