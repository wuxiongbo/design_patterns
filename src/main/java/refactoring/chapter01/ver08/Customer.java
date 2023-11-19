package refactoring.chapter01.ver08;

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
			Rental rental = rentals.nextElement();
//			frequentRenterPoints += each.getFrequentRenterPoints();
			// show figures for this rental
			result.append("\t")
					.append(rental.getMovie().getTitle()).append("\t")
					.append(rental.getCharge()).append("\n");
		}

		// add footer lines
		result.append("Amount owed is ").append(getTotalCharge()).append("\n");
		result.append("You earned ").append(getTotalFrequentRenterPoints())
				.append(" frequent renter points");
		return result.toString();
	}

	/**
	 * 重现写一个 html账单函数，实现过程中可以复用原statement()中的所有计算
	 * 非常轻松的就实现了 一个 htmlStatement()
	 * @return 账单
	 */
	public String htmlStatement() {
		Enumeration<Rental> rentals = _rentals.elements();
		StringBuilder result = new StringBuilder("<H1>Rentals for <EM>" + getName() + "</EM></ H1><P>\n");

		while (rentals.hasMoreElements()) {
			Rental each = rentals.nextElement();
			// show figures for each rental
			result.append(each.getMovie().getTitle())
					.append(": ").append(each.getCharge()).append("<BR>\n");
		}

		// add footer lines
		result.append("<P>You owe <EM>")
				.append(getTotalCharge()).append("</EM><P>\n");
		result.append("On this rental you earned <EM>")
				.append(getTotalFrequentRenterPoints())
				.append("</EM> frequent renter points<P>");
		return result.toString();
	}

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