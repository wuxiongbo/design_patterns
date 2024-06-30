package refactoring.chapter10.section7.v2;

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
        return plan.withinRange(daysTempRange());
    }


    private TempRange daysTempRange() {
        return new TempRange(low, high);
    }

    public void setLow(int low) {
        this.low = low;
    }
    public void setHigh(int high) {
        this.high = high;
    }
}
