package refactoring.chapter01.ver09.price.impl;

import refactoring.chapter01.ver09.Movie;
import refactoring.chapter01.ver09.price.Price;

public class ChildrensPrice extends Price {
	@Override
	public int getPriceCode() {
		return Movie.CHILDRENS;
	}

}
