package my_demo.decorator.demo2;

import my_demo.decorator.demo2.component.ConcreteLibrary;
import my_demo.decorator.demo2.component.AbstractLibrary;
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
 *         2.1) 可以记录 图书馆已经借出多少本书。
 *         2.2) 在原有功能的基础上，添加 “出售” 图书的功能
 *
 *
 * 如果不用装饰器，正常的思路可能是，写一个 管理系统类 提供两个方式：“出借” 跟 “归还”。
 * 后期新需求，则 派生出 扩展子类 用于记录本数，通过 重写 父类 “出借” 和 “归还” 两个方法达到记录的效果。
 *
 *
 * 现在，我们使用装饰器模式来实现。
 *
 *
 *   抽象组件                  Library<————关联、聚合
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
        Borrower person = new Borrower("Lindz");
        // 待借阅书刊
        LibraryItem book = new Book("Harry potter");
        LibraryItem journal = new Journal("Day night");



        // 图书馆（被修饰者）
        AbstractLibrary lib = new ConcreteLibrary();  // 原始的具体组件
        lib.borrowItem(book, person); // Lindz  '借阅' 书本
        lib.returnItem(journal); // '归还' 杂志


        System.out.println("--------------------------");

        // 使用 装饰器 包装 图书馆。 增加计数功能
        CountingLibrary cLib = new CountingLibrary(lib); // 修饰器
        cLib.borrowItem(book, person); // 加强 借阅 功能
        cLib.returnItem(journal); //  加强 归还 功能

        // 使用 装饰器 再次包装 图书馆。 增加售卖功能
        SellingLibrary sLib = new SellingLibrary(cLib);  // 修饰器
        sLib.sellItem(journal); // 新增 原始组件 不存在的 业务功能“出售”


        System.out.println("------------以下示例为，装饰器模式的缺点--------------");

        // 使用 装饰器 再次包装 图书馆。 增加售卖功能
        SellingLibrary sLib2 = new SellingLibrary(lib);  // 修饰器
        // 使用 装饰器 包装 图书馆。 增加计数功能
        CountingLibrary cLib2 = new CountingLibrary(sLib2); // 修饰器
        cLib2.borrowItem(book, person); // 加强 借阅 功能
        cLib2.returnItem(journal);    //  加强 归还 功能

        // 想调用 新增的方法，但是调不出来。 明明 已经实现了方法，但却隐藏不见了。
//        cLib2.sellItem()    // 新增 原始组件 不存在的 业务功能“出售”



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