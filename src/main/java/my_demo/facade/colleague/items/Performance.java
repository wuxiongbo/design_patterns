package my_demo.facade.colleague.items;

import java.util.Random;

/**
 * @Author: Battle Bear
 * @Date: 2022/4/20 22:39
 * @Description:
 */
public class Performance {
    // 基本工资
    private BasicSalary salary = new BasicSalary();

    /**
     * 绩效奖励
     *
     * @return
     */
    public int getPerformanceValue() {
        // 随机绩效
        int perf = (new Random()).nextInt(100);
        return salary.getBasicSalary() * perf / 100;
    }
}