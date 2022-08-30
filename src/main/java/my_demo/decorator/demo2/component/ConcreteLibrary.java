package my_demo.decorator.demo2.component;

import my_demo.decorator.dependence.Borrower;
import my_demo.decorator.dependence.item.LibraryItem;

/**
 * <p>具体的 图书馆</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class ConcreteLibrary extends AbstractLibrary {

    @Override
    public void borrowItem(LibraryItem item, Borrower borrower) {
        System.out.println(item.getType() + " " + item.getItemName() + " have been borrowed by " + borrower.getUserName());
    }

    @Override
    public void returnItem(LibraryItem item) {
        System.out.println(item.getType() + " " + item.getItemName() + " have been returned.");
    }
}