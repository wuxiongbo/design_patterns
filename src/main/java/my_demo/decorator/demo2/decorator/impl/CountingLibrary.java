package my_demo.decorator.demo2.decorator.impl;

import my_demo.decorator.demo2.component.AbstractLibrary;
import my_demo.decorator.demo2.decorator.BaseLibraryDecorator;
import my_demo.decorator.dependence.Borrower;
import my_demo.decorator.dependence.item.LibraryItem;

/**
 * <p> 装饰器的具体实现类：计数 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class CountingLibrary // 装饰 计数功能
        extends BaseLibraryDecorator {

    private int counter = 0;

    public CountingLibrary(AbstractLibrary lib){
        super(lib);
    }

    @Override
    public void borrowItem(LibraryItem item, Borrower borrower){

        // 委托调用 被装饰者
        super.borrowItem(item, borrower);

        // 功能增强： 借阅书本，  计数+1
        counter++;
        this.printItemsCounter();
    }

    @Override
    public void returnItem(LibraryItem item){

        // 委托 给被装饰者
        super.returnItem(item);

        // 功能增强： 归还书本， 计数-1
        counter--;
        this.printItemsCounter();
    }

    private void printItemsCounter(){
        System.out.println("the library has been borrowed " + counter + " items");
    }
}
