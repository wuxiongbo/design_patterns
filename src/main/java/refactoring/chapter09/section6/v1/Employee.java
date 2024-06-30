package refactoring.chapter09.section6.v1;

import lombok.Getter;
import lombok.Setter;
import refactoring.chapter09.section6.v1.type.EmployeeType;

/**
 * @author bear
 * @date 2024/6/30 上午9:15
 * @description
 */
@Getter
@Setter
public class Employee {
    private int _monthlySalary;
    private int _commission;
    private int _bonus;

    private EmployeeType _type;

    int getType() {
        return _type.getTypeCode();
    }
    void setType(int arg) {
        _type = EmployeeType.newType(arg);
    }

    int payAmount() {
        switch (getType()) {
            case EmployeeType.ENGINEER:
                return _monthlySalary;
            case EmployeeType.SALESMAN:
                return _monthlySalary + _commission;
            case EmployeeType.MANAGER:
                return _monthlySalary + _bonus;
            default:
                throw new RuntimeException("Incorrect Employee");
        }
    }
}
