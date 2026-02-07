package refactoring.chapter01.ver01;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bear
 */
@Getter
public class Movie {
	/**
	 * 影片分类
	 */
	public static final int REGULAR = 0;
	public static final int NEW_RELEASE = 1;
	public static final int CHILDREN = 2;

	/**
	 * 电影名称
	 */
	private final String title;
	/**
	 * 影片类型对应的价格码
	 */
	@Setter
	private final int priceCode;


	public Movie(String title, int priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

}