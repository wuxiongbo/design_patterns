package refactoring.chapter01.ver09.price.impl;

import refactoring.chapter01.ver09.Movie;
import refactoring.chapter01.ver09.price.Price;

public class NewReleasePrice extends Price {
	@Override
	public int getPriceCode() {
		return Movie.NEW_RELEASE;
	}
}