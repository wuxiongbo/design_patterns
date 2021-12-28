package my_demo.decorator.demo2.decorator;

import my_demo.decorator.demo2.component.Library;
import my_demo.decorator.dependence.Borrower;
import my_demo.decorator.dependence.item.LibraryItem;

/**
 * <p> 抽象装饰类 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public abstract class LibraryDecorator extends Library {   // “抽象装饰类”  继承 “抽象组件” ，这是可嵌套包装的关键

    // 将被装饰的 原始类
    protected Library lib;

    // 依赖注入 原始类
    public LibraryDecorator(Library lib) {
        this.lib = lib;
    }

    @Override
    public void borrowItem(LibraryItem item, Borrower borrower) {
        lib.borrowItem(item, borrower);
    }

    @Override
    public void returnItem(LibraryItem item) {
        lib.returnItem(item);
    }
}
