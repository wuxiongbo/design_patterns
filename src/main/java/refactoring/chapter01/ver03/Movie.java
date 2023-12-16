package refactoring.chapter01.ver03;

public class Movie {
    /**
     * 影片分类
     */
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDRENS = 2;

    private final String _title;
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