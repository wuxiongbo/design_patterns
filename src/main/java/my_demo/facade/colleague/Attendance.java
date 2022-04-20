package my_demo.facade.colleague;

import java.util.Random;

/**
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
