package refactoring.chapter01.ver09.price;

import refactoring.chapter01.ver09.Movie;

/**
 * 增加这一层间接性。
 */
public abstract class Price {
    public abstract int getPriceCode(); // 取得价格代号

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

}
