package my_demo.decorator.demo2.component;

import my_demo.decorator.dependence.Borrower;
import my_demo.decorator.dependence.item.LibraryItem;

/**
 * <p> 抽象的 图书馆 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public abstract class AbstractLibrary {

    // 借阅
    public abstract void borrowItem(LibraryItem item, Borrower borrower);

    // 归还
    public abstract void returnItem(LibraryItem item);
}
