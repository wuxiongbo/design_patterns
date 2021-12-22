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
public abstract class LibraryDecorator extends Library {

    protected Library lib;

    public LibraryDecorator(Library lib){
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
