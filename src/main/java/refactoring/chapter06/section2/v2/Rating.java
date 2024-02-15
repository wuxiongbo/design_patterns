package refactoring.chapter06.section2.v2;

/**
 * @author bear
 * @date 2024/2/5 01:24
 * @description
 */
public class Rating {
    int numberOfLateDeliveries;

    int getRating() {
        return (numberOfLateDeliveries > 5) ? 2 : 1;
    }
}
