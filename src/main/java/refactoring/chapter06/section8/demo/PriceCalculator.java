package refactoring.chapter06.section8.demo;

/**
 * 函数对象
 * 将所有的局部变量都变成函数对象的字段. 然后就可以对这个新对象 使用 Extract Method 创造新函数
 * @author bear
 * @date 2024/5/17 上午1:38
 * @description
 */
public class PriceCalculator {

    double primaryBasePrice;
    double secondaryBasePrice;
    double tertiaryBasePrice;

    public PriceCalculator(Order order) {

    }

    public double compute() {
        // long computation
        return primaryBasePrice + secondaryBasePrice + tertiaryBasePrice;
    }
}
