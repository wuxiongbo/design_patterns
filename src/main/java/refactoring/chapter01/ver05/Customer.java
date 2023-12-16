package refactoring.chapter01.ver05;

import java.util.*;

/**
 * 然后，以同样手法处理 frequentRentalPoints 变量：
 * 修改前：{@link refactoring.chapter01.ver05.Customer#statement()}
 * 修改后：{@link refactoring.chapter01.ver06.Customer#getTotalFrequentRenterPoints()}
 */
public class Customer {
	private final String _name; // 姓名
	private final List<Rental> _rentals = new ArrayList<>(); // 租借记

	public Customer(String name) {
		_name = name;
	};

	public void addRental(Rental arg) {
		_rentals.add(arg);
	}

	public String getName() {
		return _name;
	}

	public String statement() {
		int frequentRenterPoints = 0;

		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        for (Rental rental : _rentals) {
            frequentRenterPoints += rental.getFrequentRenterPoints();
            // show figures for this rental
            result.append("\t")
                    .append(rental.getMovie().getTitle()).append("\t")
                    .append(rental.getCharge()).append("\n");
        }

		// add footer lines
		result.append("Amount owed is ")
				.append(getTotalCharge()).append("\n");
		result.append("You earned ")
				.append(frequentRenterPoints).append(" frequent renter points");

		return result.toString();
	}

	/**
	 * 由于 result 变量 在循环内部被赋值，我们不得不把循环复制到查询函数中
	 * @return 总金额
	 *
	 * 译注：此即所谓 query method
	 */
	private double getTotalCharge() {
		double result = 0;
        for (Rental each : _rentals) {
            result += each.getCharge();
        }
		return result;
	}

}