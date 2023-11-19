package refactoring.chapter01.ver03;

import java.util.*;

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

	/**
	 * 1）修改函数引用点
	 * 	  double thisAmount = rental.getCharge();
	 * 2）去掉 多余的 thisAmount 临时变量
	 * @return
	 */
	public String statement() {
		double totalAmount = 0; // 总消费金。
		int frequentRenterPoints = 0; // 常客积点

		Iterator<Rental> rentals = _rentals.iterator();
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		while (rentals.hasNext()) {

			Rental rental = rentals.next(); // 取得一笔租借记。

			// add frequent renter points
			// 累计常客积点
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

}
