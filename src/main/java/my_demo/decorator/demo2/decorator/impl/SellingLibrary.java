package my_demo.decorator.demo2.decorator.impl;

import my_demo.decorator.demo2.component.AbstractLibrary;
import my_demo.decorator.demo2.decorator.BaseLibraryDecorator;
import my_demo.decorator.dependence.item.LibraryItem;

/**
 * <p> 装饰器的具体实现类：出售图书 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class SellingLibrary // 装饰 出售功能
        extends BaseLibraryDecorator {

    public SellingLibrary(AbstractLibrary lib) {
        super(lib);
    }

    // 新增 售卖功能
    public void sellItem(LibraryItem item) {
        System.out.println(item.getType() + " " + item.getItemName() + " has been sold");
    }

}