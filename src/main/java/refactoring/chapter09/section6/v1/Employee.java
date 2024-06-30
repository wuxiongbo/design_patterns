package refactoring.chapter09.section6.v1;

import refactoring.chapter09.section6.v1.type.EmployeeType;

/**
 * @author bear
 * @date 2024/6/30 上午9:15
 * @description
 */
public class Employee {
    private int _monthlySalary;
    private int _commission;
    private int _bonus;
    public int getMonthlySalary() {
        return _monthlySalary;
    }
    public void setMonthlySalary(int _monthlySalary) {
        this._monthlySalary = _monthlySalary;
    }
    public int getCommission() {
        return _commission;
    }
    public void setCommission(int _commission) {
        this._commission = _commission;
    }
    public int getBonus() {
        return _bonus;
    }
    public void setBonus(int _bonus) {
        this._bonus = _bonus;
    }

    private EmployeeType _type;

    int getType() {
        return _type.getTypeCode();
    }
    void setType(int arg) {
        _type = EmployeeType.newType(arg);
    }

    int payAmount() {
        return switch (getType()) {
            case EmployeeType.ENGINEER -> _monthlySalary;
            case EmployeeType.SALESMAN -> _monthlySalary + _commission;
            case EmployeeType.MANAGER -> _monthlySalary + _bonus;
            default -> throw new RuntimeException("Incorrect Employee");
        };
    }
}
