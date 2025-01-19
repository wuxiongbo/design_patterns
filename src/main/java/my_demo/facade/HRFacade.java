package my_demo.facade;

import my_demo.facade.colleague.items.Attendance;
import my_demo.facade.colleague.SalaryProvider;

import java.util.Date;

/**
 *
 * 我们采用门面模式的目的是，要求门面是 ‘无逻辑的’、‘与业务无关’，只是一个子系统的访问入口。
 * 门面模式，只是一个 技术层次上 的实现，全部业务 还是在 子系统内实现。
 *
 * @Author: Battle Bear
 * @Date: 2022/4/20 22:40
 * @Description:
 */
public class HRFacade {
    // 总工资情况
    private final SalaryProvider salaryProvider = new SalaryProvider();
    // 考勤情况
    private final Attendance attendance = new Attendance();

    /**
     * 查询一个人的总收入
     *
     * @param name
     * @param date
     * @return
     */
    public int querySalary(String name, Date date) {
        return this.salaryProvider.totalSalary();
    }

    /**
     * 查询一个员工一个月工作了多少天
     *
     * @param name
     * @return
     */
    public int queryWorkDays(String name) {
        return this.attendance.getWorkDays();
    }
}
