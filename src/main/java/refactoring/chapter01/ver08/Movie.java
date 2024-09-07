package refactoring.chapter01.ver08;

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

    /**
     * 把因条件而异的代码替换掉。 条件中调用谁的属性判断，该代码就迁移给谁
     *
     * @param daysRented
     * @return
     */
    @SuppressWarnings("Duplicates")
    public double getCharge(int daysRented) {
        double result = 0;
        switch (getPriceCode()) {
            case Movie.REGULAR -> {
                result += 2;
                if (daysRented > 2)
                    result += (daysRented - 2) * 1.5;
            }
            case Movie.NEW_RELEASE -> result += daysRented * 3;
            case Movie.CHILDRENS -> {
                result += 1.5;
                if (daysRented > 3)
                    result += (daysRented - 3) * 1.5;
            }
        }
        return result;
    }

    /**
     * 把因条件而异的代码替换掉。 条件中调用谁的属性判断，该代码就迁移给谁
     *
     * @param daysRented
     * @return
     */
    public int getFrequentRenterPoints(int daysRented) {
        if ((getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1)
            return 2;
        else
            return 1;
    }
}