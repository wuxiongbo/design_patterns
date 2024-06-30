package refactoring.chapter10.section7.v1;

/**
 * @author bear
 * @date 2024/6/30 下午6:45
 * @description
 */
public class Room {
    // 房间温度
    private int low;
    private int high;


    boolean withinPlan(HeatingPlan plan) {

        int low = daysTempRange().getLow();
        int high = daysTempRange().getHigh();

        return plan.withinRange(low, high);
    }

    private TempRange daysTempRange() {
        return new TempRange(low, high);
    }

}
