package refactoring.chapter01.ver05;

/**
 * 租赁
 */
public class Rental {
	private final Movie _movie; // 影片
	private final int _daysRented; // 租期

	public Rental(Movie movie, int daysRented) {
		_movie = movie;
		_daysRented = daysRented;
	}

	public int getDaysRented() {
		return _daysRented;
	}

	public Movie getMovie() {
		return _movie;
	}


	@SuppressWarnings("Duplicates")
	public double getCharge() {

		double result = 0;

		switch (getMovie().getPriceCode()) {

			case Movie.REGULAR -> {
				result += 2;
				if (getDaysRented() > 2)
					result += (getDaysRented() - 2) * 1.5;
			}

			case Movie.NEW_RELEASE -> result += getDaysRented() * 3;

			case Movie.CHILDRENS -> {
				result += 1.5;
				if (getDaysRented() > 3)
					result += (getDaysRented() - 3) * 1.5;
			}
		}

		return result;
	}

	public int getFrequentRenterPoints() {
		if ((getMovie().getPriceCode() == Movie.NEW_RELEASE)
				&& getDaysRented() > 1)
			return 2;
		else
			return 1;
	}
}
