package chapter50.demo2.component;

import chapter50.demo0.Demo;

import java.io.IOException;

/**
 * <p> 装饰器模式 </p>
 *
 * 本示例的 InputStream 类 是 原始类
 *
 *
 * 装饰器模式两要素：
 *   1）装饰器类 和 被装饰类(原始类) 继承同样的 父类。      （从而可多次嵌套）
 *   2）装饰器类 是对 被装饰类 相关功能的增强，而不是新增其他不相关功能
 *
 *
 *
 * InputStream 源码 简化版
 *
 * 在第 10 节中，我们还讲到“组合优于继承”，可以“使用组合来替代继承”。
 * 针对刚刚的继承结构过于复杂的问题，我们可以通过将继承关系改为组合关系来解决。
 * 下面的代码展示了 Java IO 的这种设计思路。
 *
 *
 * 注意：我对代码做了简化，只抽象出了必要的代码结构，如果你感兴趣的话，可以直接去查看 JDK 源码。
 *
 *
 * 看了BufferedInputStream 、DataInputStream的代码，你可能会问，那 “装饰器模式” 就是 简单的“用组合替代继承”吗？
 * 当然不是。从 Java IO 的设计来看，装饰器模式相对于简单的组合关系，还有两个比较特殊的地方。
 *
 * 装饰器模式 相对于 简单的组合关系，还有两个比较特殊的地方。
 *
 * 第一个比较特殊的地方是：
 *    装饰器类 和 原始类 继承同样的 父类。  对应本例中的 BufferedInputStream、FileInputStream、InputStream 类
 *    这样，我们可以对 ‘原始类’ 多次“嵌套”多个 ‘装饰器类’。
 *
 *    比如，示例{@link Demo#test2()}，我们对 FileInputStream "嵌套" 了两个装饰器类：BufferedInputStream 和 DataInputStream，
 *    让它既支持 缓存读取 ，又支持 按照基本数据类型来读取数据 。
 *
 * 第二个比较特殊的地方是：
 *    装饰器类 是对 功能的增强，这也是 装饰器模式 应用场景的一个重要特点。
 *
 * 实际上，符合“组合关系”这种代码结构的设计模式有很多，比如：代理模式、桥接模式，还有现在的 装饰器模式。
 * 尽管它们的代码结构很相似，但是每种设计模式的意图是不同的。
 *
 *
 * 就拿比较相似的 “代理模式” 和 “装饰器模式" 来说吧，
 *
 * 代理模式中，  代理类 附加的是跟 原始类 业务“无关”的其他功能，
 * 装饰器模式中， 装饰器类 附加的是跟 原始类 业务“相关”的增强功能。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/9/13
 * </pre>
 */
public abstract class InputStream {
    //...
    public abstract int read() throws IOException;

    public int read(byte b[]) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte b[], int off, int len) throws IOException {
        //...
        return 0;
    }

    public long skip(long n) throws IOException {
        //...
        return 0;
    }

    public int available() throws IOException {
        return 0;
    }

    public void close() throws IOException {}

    public synchronized void mark(int readlimit) {}

    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    public boolean markSupported() {
        return false;
    }
}
