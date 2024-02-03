package refactoring.chapter01.ver02;

import lombok.Getter;
import lombok.Setter;

public class Movie {
	/**
	 * 影片分类
	 */
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDRENS = 2;

	@Getter
	private final String title;
	@Setter
	@Getter
	private int priceCode;

	public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

}