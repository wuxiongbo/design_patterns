package my_demo.mediator;

import my_demo.mediator.colleague.items.IPosition;
import my_demo.mediator.colleague.items.ISalary;
import my_demo.mediator.colleague.items.ITax;
import my_demo.mediator.colleague.items.impl.Position;
import my_demo.mediator.colleague.items.impl.Salary;
import my_demo.mediator.colleague.items.impl.Tax;

/**
 * @Author: Battle Bear
 * @Date: 2022/4/20 21:54
 * @Description:
 */
//黑中介
public abstract class AbsMediator {
    protected final ISalary salary;	//工资
    protected final IPosition position;//职位
    protected final ITax tax;//税收
    public AbsMediator() {
        salary = new Salary(this);
        position = new Position(this);
        tax = new Tax(this);
    }
    public abstract void up(IPosition position);	//升职
    public abstract void down(IPosition position);	//降职

    public abstract void up(ISalary salary);	//加新
    public abstract void down(ISalary salary);	//降薪

    public abstract void up(ITax tax);	//升税
    public abstract void down(ITax tax);	//降税
}