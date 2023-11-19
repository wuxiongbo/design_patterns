package refactoring.chapter01.ver09.price.impl;

import refactoring.chapter01.ver09.Movie;
import refactoring.chapter01.ver09.price.Price;

public class RegularPrice extends Price {
	@Override
	public int getPriceCode() {
		return Movie.REGULAR;
	}

}