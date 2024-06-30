package refactoring.chapter09.section6.v3.type;

import refactoring.chapter09.section6.v3.Employee;

/**
 * @author bear
 * @date 2024/6/30 上午9:15
 * @description
 */
public abstract class EmployeeType {

    public static EmployeeType newType(int code) {
        switch (code) {
            case ENGINEER:
                return new Engineer();
            case SALESMAN:
                return new Salesman();
            case MANAGER:
                return new Manager();
            default:
                throw new IllegalArgumentException("Incorrect Employee Code");
        }
    }
    // 类型码复制到这里来
    public static final int ENGINEER = 0;
    public static final int SALESMAN = 1;
    public static final int MANAGER = 2;

    public abstract int getTypeCode();


    /**
     * 薪资
     * @param emp
     * @return
     */
    public int payAmount(Employee emp) {
        switch (getTypeCode()) {
            case ENGINEER, MANAGER, SALESMAN:
                throw new RuntimeException("Should be being overridden");
            default:
                throw new RuntimeException("Incorrect Employee");
        }
    }

}
