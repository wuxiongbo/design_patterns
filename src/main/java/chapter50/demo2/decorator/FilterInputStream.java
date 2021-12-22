package chapter50.demo2.decorator;

import chapter50.demo2.component.InputStream;

import java.io.IOException;

/**
 * <p> 装饰器父类 FilterInputStream </p>
 *
 * 为了避免代码重复，Java IO 抽象出了一个装饰器父类 FilterInputStream，代码实现如下所示。
 * InputStream 的所有的装饰器类（BufferedInputStream、DataInputStream）都继承自这个装饰器父类。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class FilterInputStream extends InputStream {

    // 原始类
    protected volatile InputStream in;

    // 依赖注入 原始类
    protected FilterInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        // 调用  原始类的 read()方法，而不是 父类的 read()方法
//        return super.read();
        return in.read();
    }

    @Override
    public int read(byte b[]) throws IOException {
        return in.read(b, 0, b.length);
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
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
