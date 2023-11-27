package refactoring.chapter01.ver10.price.impl;

import refactoring.chapter01.ver10.Movie;
import refactoring.chapter01.ver10.price.Price;

public class NewReleasePrice implements Price {
	public int getPriceCode() {
		return Movie.NEW_RELEASE;
	}

	public double getCharge(int daysRented) {
		return daysRented * 3;
	}
}