package refactoring.chapter06.section4.v3;

/**
 * @author bear
 * @date 2024/3/24 02:17
 * @description
 */
public class Demo {

    private final int quantity;
    private final int itemPrice;

    public Demo(int quantity, int itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public double getPrice() {
        return basePrice() * discountFactor();
    }

    private double discountFactor() {
        final double discountFactor;
        if (basePrice() > 1000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return discountFactor;
    }

    private int basePrice() {
        return quantity * itemPrice;
    }


}
