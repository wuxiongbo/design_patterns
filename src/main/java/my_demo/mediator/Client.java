package my_demo.mediator;


import my_demo.mediator.colleague.items.IPosition;
import my_demo.mediator.colleague.items.ISalary;
import my_demo.mediator.colleague.items.ITax;
import my_demo.mediator.colleague.items.impl.Position;
import my_demo.mediator.colleague.items.impl.Salary;
import my_demo.mediator.colleague.items.impl.Tax;

/**
 * @Author: Battle Bear
 * @Date: 2022/4/20 21:55
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        AbsMediator mediator = new AbsMediator(){
            @Override
            public void up(IPosition position) {
                System.out.println("职位上升");
                salary.increaseSalary();
                tax.raise();
            }

            @Override
            public void down(IPosition position) {
                System.out.println("职位下降");
            }

            @Override
            public void up(ISalary salary) {
                System.out.println("工资加倍");
                System.out.println("税收增加");
            }

            @Override
            public void down(ISalary salary) {
                System.out.println("工资缩减");
            }

            @Override
            public void up(ITax tax) {
                System.out.println("税收增加");
                System.out.println("工资缩减");
            }

            @Override
            public void down(ITax tax) {
                System.out.println("税收减少");
            }
        };

        IPosition position = new Position(mediator);
        ISalary salary = new Salary(mediator);
        ITax tax = new Tax(mediator);
        System.out.println("-----职位提升-----");
        position.promote();
        System.out.println("-----工资提升-----");
        salary.increaseSalary();
        System.out.println("-----税收提升-----");
        tax.raise();
    }

}
