package my_demo.decorator.demo2.decorator.impl;

import my_demo.decorator.demo2.component.Library;
import my_demo.decorator.demo2.decorator.LibraryDecorator;
import my_demo.decorator.dependence.item.LibraryItem;

/**
 * <p> 具体装饰器类：出售图书 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class SellingLibrary extends LibraryDecorator {

    public SellingLibrary(Library lib) {
        super(lib);
    }

    // 新增 售卖功能
    public void sellItem(LibraryItem item) {
        System.out.println(item.getType() + " " + item.getItemName() + " has been sold");
    }

}