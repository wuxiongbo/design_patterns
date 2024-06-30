package refactoring.chapter09.section6.v3.type;

import refactoring.chapter09.section6.v3.Employee;

/**
 * @author bear
 * @date 2024/6/30 上午9:16
 * @description
 */
public class Engineer extends EmployeeType {
    @Override
    public int getTypeCode() {
        return ENGINEER;
    }

    @Override
    public int payAmount(Employee emp) {
        return emp.getMonthlySalary();
    }
}
