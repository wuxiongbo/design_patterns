package chapter50.demo2.decorator.impl;

import chapter50.demo2.component.InputStream;
import chapter50.demo2.decorator.FilterInputStream;

import java.io.IOException;

/**
 * <p> 装饰器： 缓存增强 </p>
 *
 * InputStream 是一个抽象类而非接口，而且它的大部分函数（比如 read()、available()）都有默认实现，
 * 按理来说，我们只需要在 BufferedInputStream 类中重新实现那些需要增加缓存功能的函数就可以了，其他函数继承 InputStream 的默认实现。
 * 但实际上，这样做是行不通的。
 *
 *
 * 对于即便是不需要增加缓存功能的函数来说，BufferedInputStream 还是必须把它重新实现一遍，简单包裹对 InputStream 对象的函数调用。
 * 具体的代码如f()所示。
 * 如果不重新实现，那 BufferedInputStream 类就无法将最终读取数据的任务，委托给传递进来的 InputStream 对象来完成。
 * 这一部分稍微有点不好理解。
 *
 * 问：为什么要重新实现？
 * 装饰器 如 BufferedInputStream 等，本身并不真正处理read()等方法，而是委托 构造函数传入的 被装饰对象 FilterInputStream等 来完成的。
 * 如果不重写默认的read()方法，则使用的是 “装饰器父类”的read()方法，而非 “被装饰对象”的read()方法。
 * 则无法 委托 给 FileInputStream或ByteArrayInputStream等对象 实现的read功能 完成读取操作。
 * 所以，必须重写对应的方法，委托给这些被装饰对象进行处理。
 *
 * 问：为什么继承装饰器父类 FilterInputStream ?
 * 如果像DataInputStream和BufferedInputStream等，每个装饰器都重写的这些方法话，会存在大量重复的代码。
 * 所以，让它们都继承FilterInputStream 提供的默认实现，可以减少代码重复，让装饰器只聚焦在它自己的装饰功能上即可。
 *
 *
 * 实际上，DataInputStream 也存在跟 BufferedInputStream 同样的问题。
 *
 * 为了避免代码重复，Java IO 抽象出了一个装饰器父类 FilterInputStream
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/9/13
 * </pre>
 */
public class BufferedInputStream extends FilterInputStream {

    public BufferedInputStream(InputStream in) {
        super(in);
    }


    //...实现基于缓存的读数据接口...


    // read()函数 不需要增强，只是重新调用一下InputStream in对象的 read()函数
    @Override
    public int read() throws IOException {
        return super.read();
    }


    @Override
    public int read(byte b[]) throws IOException {

        //....增强操作

        int read = super.read();

        //....增强操作

        return 0;
    }

}
