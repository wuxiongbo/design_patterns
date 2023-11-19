package refactoring.chapter01.ver04;

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

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = _rentals.iterator();
		StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

		while (rentals.hasNext()) {

			Rental rental = rentals.next();

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
