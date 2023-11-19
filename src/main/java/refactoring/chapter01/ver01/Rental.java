package refactoring.chapter01.ver01;

/**
 * 租赁
 * 表示某个顾客租了一部影片
 */
public class Rental {
	private final Movie _movie; // 影片
	private final int _daysRented; // 租期

	public Rental(Movie movie, int daysRented) {
		_movie = movie;
		_daysRented = daysRented;
	}

	/**
	 * 获取租期
	 * @return 租期
	 */
	public int getDaysRented() {
		return _daysRented;
	}

	public Movie getMovie() {
		return _movie;
	}
}
