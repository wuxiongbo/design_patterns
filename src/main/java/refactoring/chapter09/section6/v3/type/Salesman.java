package refactoring.chapter09.section6.v3.type;

import refactoring.chapter09.section6.v3.Employee;

/**
 * @author bear
 * @date 2024/6/30 上午9:23
 * @description
 */
public class Salesman extends EmployeeType {

    @Override
    public int getTypeCode() {
        return SALESMAN;
    }

    @Override
    public int payAmount(Employee emp) {
        return emp.getMonthlySalary() + emp.getCommission();
    }
}
