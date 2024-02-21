package refactoring.chapter06.section3.v2;

/**
 * Inline Temp（119）多半是作为 Replace Temp with Query（120）的⼀部分使⽤的，所以,真正的动机出现在后者那⼉。
 * 唯⼀单独使⽤Inline Temp（119）的情况是：
 * 你发现某个临时变量被赋予某个函数调⽤的返回值。
 * ⼀般来说，这样的临时变量不会有任何危害，可以放⼼地把它留在那⼉。
 * 但,如果这个临时变量妨碍了其他的重构⼿法，例如 Extract Method （110），你就应该将它内联化。
 * <p>
 *
 * @author bear
 * @date 2024/2/16 16:59
 * @description
 */
public class Demo {
    public boolean getPrice(Order anorder) {
        return (anorder.basePrice() > 1000);
    }

    public static class Order {
        double basePrice;

        public double basePrice() {
            return basePrice;
        }
    }

}
