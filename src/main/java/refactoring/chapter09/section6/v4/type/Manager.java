package refactoring.chapter09.section6.v4.type;

import refactoring.chapter09.section6.v4.Employee;

/**
 * @author bear
 * @date 2024/6/30 上午9:16
 * @description
 */
public class Manager extends EmployeeType {

    @Override
    public int getTypeCode() {
        return MANAGER;
    }

    public int payAmount(Employee emp) {
        return emp.getMonthlySalary() + emp.getBonus();
    }

}
