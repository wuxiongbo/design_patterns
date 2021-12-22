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
public class DataInputStream extends FilterInputStream {

    protected DataInputStream(InputStream in) {
        super(in);
    }

    //...实现读取基本类型数据的接口

}