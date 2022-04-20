package chapter50.demo2.decorator.impl;

import chapter50.demo2.component.InputStream;
import chapter50.demo2.decorator.FilterInputStream;


/**
 * <p> 装饰器： 数据类型读取增强 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/9/13
 * </pre>
 */
public class DataInputStream
        extends FilterInputStream {  // 继承装饰器父类

    /**
     *
     * @param in   依赖注入  被装饰的类
     */
    public DataInputStream(InputStream in) {
        // 依赖注入 原始类(被委托类)
        super(in);
    }


    //...实现读取基本类型数据的接口

}