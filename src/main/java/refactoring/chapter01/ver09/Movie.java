package refactoring.chapter01.ver09;

import refactoring.chapter01.ver09.price.impl.ChildrensPrice;
import refactoring.chapter01.ver09.price.impl.NewReleasePrice;
import refactoring.chapter01.ver09.price.Price;
import refactoring.chapter01.ver09.price.impl.RegularPrice;

public class Movie {

    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDRENS = 2;

    private final String _title;

    // 修改前
//    private int _priceCode;

    // 修改后：
    // Movie类内，保存一个Price对象，不再是保存一个 _priceCode 变量
    private Price _price;

    public Movie(String title, int priceCode) {

        _title = title;

        // 译注：这就是一个 set method
        setPriceCode(priceCode);
    }

    // ========start============

//    public Movie(String title, int priceCode) {
//        _title = title;
//        _priceCode = priceCode;
//    }
//    public int getPriceCode() {
//        return _priceCode;
//    }
//    public setPriceCode(int priceCode) {
//        _priceCode = priceCode;
//    }
// ========上面注释，修改为以下代码============

    public int getPriceCode() { // 取得价格代号
        return _price.getPriceCode();
    }

    // 设定价格代号
    public void setPriceCode(int priceCode) {
        switch (priceCode) {
            case REGULAR -> _price = new RegularPrice();
            case CHILDRENS -> _price = new ChildrensPrice();
            case NEW_RELEASE -> _price = new NewReleasePrice();
            default -> throw new IllegalArgumentException("Incorrect Price Code");
        }
    }
    // ========end============


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