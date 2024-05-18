package refactoring.chapter06.section8.demo;

/**
 * @author bear
 * @date 2024/5/17 上午1:37
 * @description
 */
public class Order {

    double price() {
        return new PriceCalculator(this).compute();
    }

}
