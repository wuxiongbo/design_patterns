package refactoring.chapter01.ver04;

import java.util.*;

/**
 * 去除临时变量
 *
 * 正如我在前面提过的，临时变量可能是个问题。
 * 它们只在自己所属的函数中有效，所以，它们会助长冗长而复杂的函数。
 * 这里有两个临时变量，两者都是用来从 Customer 对象相关的 Rental对象中 获得某个总量。
 * 不论 ASCII版 或 HTML版 ，都需要这些总量。
 * 我打算运用 Replace Temp with Query(120),
 * 并利用 查询函数(query method) 来取代 totalAmount 和 frequentRentalPoints 这两个临时变量。
 * 由于，类中的任何函数都可以调用上述 查询函数，所以，它能够促成较干净的设计，而减少冗长复杂的函数：
 *
 * 首先，我用 Customer类的 getTotalCharge()查询函数，取代 totalAmount 变量：
 * 修改前：{@link Customer#statement()}
 * 修改后：{@link refactoring.chapter01.ver05.Customer#getTotalCharge()}
 *
 * 这并不是 Replace Temp with Query(120) 的最简单情况。
 * 由于，totalAmount 在循环内部被赋值，我不得不把循环复制到查询函数中。
 * 重构之后，重新编译并测试
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

		double totalAmount = 0;
		int frequentRenterPoints = 0;

		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

		for (Rental rental : _rentals){

			frequentRenterPoints += rental.getFrequentRenterPoints();

			// show figures for this rental
			result.append("\t")
					.append(rental.getMovie().getTitle()).append("\t")
					.append(rental.getCharge()).append("\n");

			totalAmount += rental.getCharge();
		}

		// add footer lines
		result.append("Amount owed is ")
				.append(totalAmount).append("\n");
		result.append("You earned ")
				.append(frequentRenterPoints).append(" frequent renter points");

		return result.toString();
	}

}
