package my_demo.mediator;


import my_demo.mediator.colleague.items.IPosition;
import my_demo.mediator.colleague.items.ISalary;
import my_demo.mediator.colleague.items.ITax;
import my_demo.mediator.colleague.items.impl.Position;
import my_demo.mediator.colleague.items.impl.Salary;
import my_demo.mediator.colleague.items.impl.Tax;
import my_demo.mediator.mediator.AbsMediator;
import my_demo.mediator.mediator.Mediator;

/**
 * @Author: Battle Bear
 * @Date: 2022/4/20 21:55
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        AbsMediator mediator = new Mediator();

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
