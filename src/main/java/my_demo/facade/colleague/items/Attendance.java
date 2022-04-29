package my_demo.facade.colleague.items;

import java.util.Random;

/**
 * 考勤
 * @Author: Battle Bear
 * @Date: 2022/4/20 22:38
 * @Description:
 */
public class Attendance {
    /**
     * 得到出勤天数
     *
     * @return
     */
    public int getWorkDays() {
        return (new Random()).nextInt(30);
    }
}
