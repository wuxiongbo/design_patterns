package refactoring.chapter10.section7.v1;

/**
 * @author bear
 * @date 2024/6/30 下午6:45
 * @description
 */
public class HeatingPlan {

    public boolean withinRange(int low, int high) {
        return (low >= _range.getLow() && high <= _range.getHigh());
    }

    // 预设温度区间
    private TempRange _range;
    public void setRange(TempRange _range) {
        this._range = _range;
    }
}
