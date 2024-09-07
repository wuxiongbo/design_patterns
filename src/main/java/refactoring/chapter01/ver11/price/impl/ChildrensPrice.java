package refactoring.chapter01.ver11.price.impl;

import refactoring.chapter01.ver11.Movie;
import refactoring.chapter01.ver11.price.Price;

public class ChildrensPrice implements Price {

	public int getPriceCode() {
		return Movie.CHILDRENS;
	}

	public double getCharge(int daysRented) {
		double result = 1.5;
		if (daysRented > 3)
			result += (daysRented - 3) * 1.5;
		return result;
	}

}
