package refactoring.chapter09.section6.v1.type;

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
}
