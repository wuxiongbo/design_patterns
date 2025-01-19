package refactoring.chapter01.ver07;

/**
 * 租赁
 */
public class Rental {

	// 影片
	private final Movie _movie;

	// 租期
	private final int _daysRented;

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

	/**
	 * 当前计算 依赖 影片分类规则。
	 * move to {@link refactoring.chapter01.ver08.Movie#getCharge(int)}
	 * @return
	 */
	@SuppressWarnings("Duplicates")
	public double getCharge() {
		double result = 0;
		// 影片类型
		switch (getMovie().getPriceCode()) {
			case Movie.REGULAR -> {
				result += 2;
				// 租期长度
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

	/**
	 * 当前计算 依赖 影片分类规则。
	 * move to {@link refactoring.chapter01.ver08.Movie#getFrequentRenterPoints(int)}
	 * @return
	 */
	public int getFrequentRenterPoints() {
		if ((getMovie().getPriceCode() == Movie.NEW_RELEASE)
				&& getDaysRented() > 1)
			return 2;
		else
			return 1;
	}
}
