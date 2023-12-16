package refactoring.chapter01.ver11.price.impl;

import refactoring.chapter01.ver11.Movie;
import refactoring.chapter01.ver11.price.Price;

public class NewReleasePrice implements Price {
	public int getPriceCode() {
		return Movie.NEW_RELEASE;
	}

	public double getCharge(int daysRented) {
		return daysRented * 3;
	}

	/**
	 * 重写默认实现
	 * @param daysRented 租赁期
	 * @return 积分数
	 */
	public int getFrequentRenterPoints(int daysRented) {
		return (daysRented > 1) ? 2 : 1;
	}
}