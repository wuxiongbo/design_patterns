package my_demo.facade.colleague;

import my_demo.facade.colleague.items.BasicSalary;
import my_demo.facade.colleague.items.Bonus;
import my_demo.facade.colleague.items.Performance;
import my_demo.facade.colleague.items.Tax;

/**
 * 薪水 服务提供者
 * @Author: Battle Bear
 * @Date: 2022/4/20 22:39
 * @Description:
 */
public class SalaryProvider {
    // 基本工資
    private BasicSalary basicSalary = new BasicSalary();
    // 奖金
    private Bonus bonus = new Bonus();
    // 绩效
    private Performance perf = new Performance();
    // 税收
    private Tax tax = new Tax();

    /**
     * 获得用户总收入
     *
     * 这里只是对前面的元素值做了一个加减法计算，这是对实际HR系统的简化处理，
     * 如果把这个类暴露给外系统，那么被修改的风险是非常大的，因为他的方法totalSalary是一个具体的业务逻辑。
     *
     *
     * 我们采用门面模式的目的是要求门面是无逻辑的，与业务无关，只是一个子系统的访问入口。
     * 门面模式只是一个技术层次上的实现，全部业务还是在子系统内实现。
     *
     *
     * @return
     */
    public int totalSalary() {
        return this.basicSalary.getBasicSalary() + this.bonus.getBonus()
                + this.perf.getPerformanceValue() - this.tax.getTax();
    }
}
