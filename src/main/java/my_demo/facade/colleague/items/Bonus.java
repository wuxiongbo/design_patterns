package my_demo.facade.colleague.items;

import my_demo.facade.colleague.Attendance;

/**
 * @Author: Battle Bear
 * @Date: 2022/4/20 22:38
 * @Description:
 */
public class Bonus {
    // 考勤情况
    private Attendance atte = new Attendance();

    /**
     * 奖金
     *
     * @return
     */
    public int getBonus() {
        // 获得出勤情况
        int workDays = atte.getWorkDays();
        // 奖金计算模型
        int bonus = workDays * 1800 / 30;
        return bonus;
    }
}