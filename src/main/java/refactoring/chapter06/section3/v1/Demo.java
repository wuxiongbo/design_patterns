package refactoring.chapter06.section3.v1;

/**
 * @author bear
 * @date 2024/2/16 16:59
 * @description
 */
public class Demo {
    public boolean getPrice(Order anorder) {
        double basePrice = anorder.basePrice();
        return (basePrice > 1000);
    }

    public static class Order {
        double basePrice;

        public double basePrice() {
            return basePrice;
        }
    }
}
