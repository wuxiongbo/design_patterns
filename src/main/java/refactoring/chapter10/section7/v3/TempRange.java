package refactoring.chapter10.section7.v3;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bear
 * @date 2024/6/30 下午6:45
 * @description
 */
@Getter
@Setter
public class TempRange {
    private int low;
    private int high;

    public TempRange(int low, int high) {
        this.low = low;
        this.high = high;
    }

    boolean includes(TempRange arg) {
        return arg.getLow() >= this.getLow() && arg.getHigh() <= this.getHigh();
    }

}
