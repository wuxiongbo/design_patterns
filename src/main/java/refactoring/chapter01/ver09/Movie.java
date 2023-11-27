package refactoring.chapter01.ver09;

import refactoring.chapter01.ver09.price.impl.ChildrensPrice;
import refactoring.chapter01.ver09.price.impl.NewReleasePrice;
import refactoring.chapter01.ver09.price.Price;
import refactoring.chapter01.ver09.price.impl.RegularPrice;

public class Movie {

	public static final int CHILDRENS = 2;
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;

	private final String _title;

	public Movie(String title, int priceCode) {
		_title = title;
		setPriceCode(priceCode); // 译注：这就是一个set method
	}

	private Price _price;

	public int getPriceCode() { // 取得价格代号
		return _price.getPriceCode();
	}

	public void setPriceCode(int arg) { // 设定价格代号
		switch (arg) {
			case REGULAR -> _price = new RegularPrice();
			case CHILDRENS -> _price = new ChildrensPrice();
			case NEW_RELEASE -> _price = new NewReleasePrice();
			default -> throw new IllegalArgumentException("Incorrect Price Code");
		}
	}

	public String getTitle() {
		return _title;
	}

	public double getCharge(int daysRented) {
		return _price.getCharge(daysRented);
	}

	public int getFrequentRenterPoints(int daysRented) {
		if ((getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1)
			return 2;
		else
			return 1;
	}
}