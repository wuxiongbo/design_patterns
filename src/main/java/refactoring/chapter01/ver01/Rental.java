package refactoring.chapter01.ver01;

import lombok.Getter;

/**
 * 租赁
 * 表示某个顾客租了一部影片
 */
@Getter
public class Rental {

	/**
	 * 影片
	 */
	private final Movie movie;
	/**
	 * 租期
	 */
	private final int daysRented;


	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

}
