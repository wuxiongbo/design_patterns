package my_demo.decorator.demo2.decorator.impl;

import my_demo.decorator.demo2.component.Library;
import my_demo.decorator.demo2.decorator.LibraryDecorator;
import my_demo.decorator.dependence.Borrower;
import my_demo.decorator.dependence.item.LibraryItem;

/**
 * <p> 具体装饰器类：计数 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class CountingLibrary extends LibraryDecorator {
    private int counter = 0;

    public CountingLibrary(Library lib){
        super(lib);
    }

    @Override
    public void borrowItem(LibraryItem item, Borrower borrower){

        // 委托 给被装饰者
        this.lib.borrowItem(item, borrower);

        // 功能增强： 借阅书本，  计数+1
        counter++;
        this.printItemsCounter();
    }

    @Override
    public void returnItem(LibraryItem item){

        // 委托 给被装饰者
        this.lib.returnItem(item);

        // 功能增强： 归还书本， 计数-1
        counter--;
        this.printItemsCounter();
    }

    private void printItemsCounter(){
        System.out.println("the library has been borrowed " + counter + " items");
    }
}
