package refactoring.chapter01.ver02;

import java.util.*;

public class Customer {
	private final String _name; // 姓名
	private final List<Rental> _rentals = new ArrayList<>(); // 租借记录

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
		double totalAmount = 0; // 总消费金。
		int frequentRenterPoints = 0; // 常客积点

		Iterator<Rental> rentals = _rentals.iterator();
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

		while (rentals.hasNext()) {
			// 取得一笔租借记。
			Rental each = rentals.next();

			double thisAmount = amountFor(each);

			// add frequent renter points
			// 累计常客积点。
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE)
					&& each.getDaysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			// 显示此笔租借记录
			result.append("\t")
					.append(each.getMovie().getTitle()).append("\t")
					.append(thisAmount).append("\n");

			totalAmount += thisAmount;
		}

		// add footer lines（结尾打印）
		result.append("Amount owed is ").append(totalAmount).append("\n");
		result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");

		return result.toString();
	}

	/**
	 * 根据租赁记录计算费用
	 *
	 * 1）抽取方法
	 * 2）修改变量名
	 * @param aRental  一个租赁实例
	 * @return
	 */
	private double amountFor(Rental aRental) {
		double result = 0;
		// determine amounts for each line
		switch (aRental.getMovie().getPriceCode()) { // 取得影片出租价格

			case Movie.REGULAR -> { // 普通片
				result += 2;
				if (aRental.getDaysRented() > 2)
					result += (aRental.getDaysRented() - 2) * 1.5;
			}

			case Movie.NEW_RELEASE -> // 新片
					result += aRental.getDaysRented() * 3;

			case Movie.CHILDRENS -> { // 儿童。
				result += 1.5;
				if (aRental.getDaysRented() > 3)
					result += (aRental.getDaysRented() - 3) * 1.5;
			}
		}

		return result;
	}
}
