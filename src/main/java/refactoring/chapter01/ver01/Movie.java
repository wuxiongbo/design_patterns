package refactoring.chapter01.ver01;

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
	private final String _title;
	/**
	 * 影片类型对应的价格码
	 */
	private int _priceCode;

	public Movie(String title, int priceCode) {
		_title = title;
		_priceCode = priceCode;
	}

	public int getPriceCode() {
		return _priceCode;
	}

	public String getTitle() {
		return _title;
	}

	public void setPriceCode(int priceCode) {
		_priceCode = priceCode;
	}

}