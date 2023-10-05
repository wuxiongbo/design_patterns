package my_demo.decorator.demo2.decorator;

import my_demo.decorator.demo2.component.AbstractLibrary;
import my_demo.decorator.dependence.Borrower;
import my_demo.decorator.dependence.item.LibraryItem;

/**
 * <p> 装饰器的抽象类 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public abstract class BaseLibraryDecorator  // 图书馆装饰器 抽象
        extends AbstractLibrary {   // “抽象装饰类”  继承 “抽象组件” ，这是可嵌套包装的关键

    // 将被装饰的 原始类/被装饰过的类
    protected AbstractLibrary lib;
    // 依赖注入 原始类/被装饰过的类
    public BaseLibraryDecorator(AbstractLibrary lib) {
        this.lib = lib;
    }



    @Override
    public void borrowItem(LibraryItem item, Borrower borrower) {
        // 委托
        lib.borrowItem(item, borrower);
    }

    @Override
    public void returnItem(LibraryItem item) {
        // 委托
        lib.returnItem(item);
    }
}
