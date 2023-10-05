package the_beauty_of_design_patterns.chapter50.demo2.decorator;

import the_beauty_of_design_patterns.chapter50.demo2.component.InputStream;

import java.io.IOException;

/**
 * <p> 装饰器父类 FilterInputStream </p>
 *
 * 为了避免代码重复， Java IO 在 与 InputStream之间，加了一层，又抽象出了一个装饰器父类 FilterInputStream，
 *
 *
 * 代码实现如下所示。
 *    主要逻辑是，将函数的实现 委托给 原始类(被委托者)
 *
 *
 * InputStream 的所有的装饰器类（BufferedInputStream、DataInputStream）都继承自这个  装饰器父类。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class FilterInputStream  // Filter 过滤器。 委托对象
        extends InputStream {   // 抽象装饰类  继承了  抽象组件。  这是可嵌套包装的关键

    // 原始类。 被委托对象
    protected volatile InputStream in;   // 组合关系

    // 依赖注入 原始类
    protected FilterInputStream(InputStream in) {
        this.in = in;
    }



    @Override
    public int read() throws IOException {

        // 错误示例：不能调用 父类InputStream的 read()方法
//        return super.read();
        // 而是调用  原始类InputStream的  read()方法
        // 理解这个的关键是，我们要清楚的意识到，被委托对象是谁？
        return in.read();
    }

    @Override
    public int read(byte b[]) throws IOException {
        return in.read(b, 0, b.length);
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        // 错误示例：不能调用 父类InputStream的 read()方法
//        return super.read(b, off, len);
        // 而是调用  原始类InputStream的  read()方法
        return in.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    @Override
    public int available() throws IOException {
        return in.available();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        in.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        in.reset();
    }

    @Override
    public boolean markSupported() {
        return in.markSupported();
    }
}
