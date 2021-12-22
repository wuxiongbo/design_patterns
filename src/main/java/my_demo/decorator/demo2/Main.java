package my_demo.decorator.demo2;

import my_demo.decorator.demo2.component.ConcreteLibrary;
import my_demo.decorator.demo2.component.Library;
import my_demo.decorator.demo2.decorator.impl.CountingLibrary;
import my_demo.decorator.demo2.decorator.impl.SellingLibrary;
import my_demo.decorator.dependence.item.Book;
import my_demo.decorator.dependence.Borrower;
import my_demo.decorator.dependence.item.Journal;
import my_demo.decorator.dependence.item.LibraryItem;

/**
 * <p> 装饰器模式 </p>
 *
 *
 * 你被要求开发一个简单图书馆管理系统，初步有以下的要求:
 *      1）实现书的 “借阅” 和 “归还”
 *      2）后期新需求。
 *         如：   可以记录 图书馆已经借出多少本书、
 *         再如： 在原有功能的基础上添加，出售图书的功能
 *
 *
 * 如果不用装饰器，正常的思路可能是，写一个 管理系统类 提供两个方式：“出借” 跟 “归还”。
 * 后期新需求，则 派生出 扩展子类 用于记录本数，通过 重写 父类 “出借” 和 “归还” 两个方法达到记录的效果。
 *
 *
 * 现在，我们使用装饰器模式来实现。
 *
 *
 *   抽象组件                  Library<————聚合
 *                           /      \        |
 *                        泛化      泛化      |
 *                        /           \     <>
 *   具体组件   ConcreteLibrary        LibraryDecorator                   抽象装饰器
 *                                       /          \
 *                                     泛化         泛化
 *                                    /               \
 *                                 CountingLibrary    SellingLibrary     具体装饰器
 *
 *
 *                 被装饰类                 装饰类
 *
 *
 * https://zhuanlan.zhihu.com/p/25003369
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class Main {
    public static void main(String[] args){
        // 借阅者
        Borrower borrower = new Borrower("Lindz");

        // 借阅书籍
        LibraryItem book = new Book("Harry potter");
        LibraryItem journal = new Journal("Day night");

        // 实例化图书馆
        Library lib = new ConcreteLibrary();
        lib.borrowItem(book, borrower); // 借阅
        lib.returnItem(journal); // 归还


        System.out.println("--------------------------");

        // 使用 装饰器 包装 图书馆
        CountingLibrary clib = new CountingLibrary(lib);
        clib.borrowItem(book, borrower); // 借阅
        clib.returnItem(journal); // 归还

        // 使用 装饰器 再次包装 图书馆
        SellingLibrary slib = new SellingLibrary(clib);
        slib.sellItem(journal); // 出售

    }
}

/*

Book Harry potter have been borrowed by Lindz
Journal Day night have been returned.
--------------------------
Book Harry potter have been borrowed by Lindz
the library has been borrowed 1 items
Journal Day night have been returned.
the library has been borrowed 0 items
Journal Day night has been sold

 */