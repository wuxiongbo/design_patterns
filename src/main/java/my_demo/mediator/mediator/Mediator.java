package my_demo.mediator.mediator;

import my_demo.mediator.colleague.items.IPosition;
import my_demo.mediator.colleague.items.ISalary;
import my_demo.mediator.colleague.items.ITax;

/**
 * @Author: Battle Bear
 * @Date: 2022/4/20 22:33
 * @Description:
 */
public class Mediator extends AbsMediator {

    @Override
    public void up(ISalary salary) {
        this.upSalary();
        this.upTax();
    }

    @Override
    public void up(IPosition position) {
        this.upPosition();
        this.upSalary();
        this.upTax();
    }

    @Override
    public void up(ITax tax) {
        this.upTax();
        this.downSalary();
    }

    @Override
    public void down(ISalary salary) {
        this.downSalary();
        this.downTax();
    }

    @Override
    public void down(IPosition position) {
        this.downPosition();
        this.downSalary();
        this.downTax();
    }

    @Override
    public void down(ITax tax) {
        this.downTax();
        this.upSalary();
    }

    /**
     * 工资增加
     */
    private void upSalary() {
        System.out.println("工资增加");
    }

    /**
     * 税收上升
     */
    private void upTax() {
        System.out.println("税收上升");
    }

    /**
     * 职位上升
     */
    private void upPosition() {
        System.out.println("职位上升");
    }

    /**
     * 降低工资
     */
    private void downSalary() {
        System.out.println("降低工资");
    }

    /**
     * 降低税收
     */
    private void downTax() {
        System.out.println("降低税收");
    }

    /**
     * 职位降低
     */
    private void downPosition() {
        System.out.println("职位降低");
    }
}