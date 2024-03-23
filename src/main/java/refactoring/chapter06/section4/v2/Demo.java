package refactoring.chapter06.section4.v2;

/**
 * @author bear
 * @date 2024/3/24 02:17
 * @description
 */
public class Demo {

    private int _quantity;
    private int itemPrice;

    double getPrice() {
        final int basePrice = _quantity * itemPrice;

        final double discountFactor;
        if (basePrice > 1000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }

        return basePrice * discountFactor;
    }



}
