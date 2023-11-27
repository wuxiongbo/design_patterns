package refactoring.chapter01.ver06;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {
	private final String _name; // 姓名
	private final Vector<Rental> _rentals = new Vector<>(); // 租借记

	public Customer(String name) {
		_name = name;
	};

	public void addRental(Rental arg) {
		_rentals.addElement(arg);
	}

	public String getName() {
		return _name;
	}

	public String statement() {

//		int frequentRenterPoints = 0;
		Enumeration<Rental> rentals = _rentals.elements();
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
		while (rentals.hasMoreElements()) {
			Rental each = rentals.nextElement();

//			frequentRenterPoints += each.getFrequentRenterPoints();

			// show figures for this rental
			result.append("\t").append(each.getMovie().getTitle())
					.append("\t").append(each.getCharge()).append("\n");
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
	 * 用同样的手法处理  frequentRenterPoints 变量 为查询函数
	 * @return 积分
	 */
	// 译注：此即所谓query method
	private int getTotalFrequentRenterPoints() {
		int result = 0;
		Enumeration<Rental> rentals = _rentals.elements();
		while (rentals.hasMoreElements()) {
			Rental each = rentals.nextElement();
			result += each.getFrequentRenterPoints();
		}
		return result;
	}

	// 译注：此即所谓query method
	private double getTotalCharge() {
		double result = 0;
		Enumeration<Rental> rentals = _rentals.elements();
		while (rentals.hasMoreElements()) {
			Rental each = rentals.nextElement();
			result += each.getCharge();
		}
		return result;
	}

}