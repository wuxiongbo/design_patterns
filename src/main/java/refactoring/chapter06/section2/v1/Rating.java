package refactoring.chapter06.section2.v1;

/**
 * @author bear
 * @date 2024/2/5 01:24
 * @description
 */
public class Rating {
    int numberOfLateDeliveries;
    int getRating(){
        return (moreThanFiveLateDeliveries()) ? 2: 1;
    }

    boolean moreThanFiveLateDeliveries(){
        return numberOfLateDeliveries > 5;
    }

}
