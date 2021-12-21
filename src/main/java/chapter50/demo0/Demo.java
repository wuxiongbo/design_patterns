package chapter50.demo0;

import chapter10.demo1.Main;
import chapter50.dependence.BufferedFileInputStream;

import java.io.*;

/**
 * <p> 装饰器模式 的经典案例：io流 </p>
 *
 *
 * 示例：打开文件 test.txt，从中读取数据
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/9/13
 * </pre>
 */
public class Demo {

    /**
     *
     * JDK 的设计：
     *
     *
     * InputStream 是一个抽象类
     *      |
     *     继承
     *      |
     * FileInputStream 是专门用来读取文件流的子类
     *
     *
     *
     * InputStream
     *      |
     *     继承
     *      |
     * BufferedInputStream 是一个支持带缓存功能的数据读取类，可以提高数据读取的效率
     *
     *
     *
     * 使用起来，看起来好像比较麻烦，每次功能增强都要包装一次。
     * @throws IOException
     */
    public void test() throws IOException {

        InputStream in = new FileInputStream("/user/wangzheng/test.txt");

        // 功能增强：缓存
        InputStream bin = new BufferedInputStream(in);

        byte[] data = new byte[128];
        while (bin.read(data) != -1) {
            //...
        }

    }


    /**
     *
     * 假想的设计：
     *
     * InputStream
     *      |
     *     继承
     *      |
     * FileInputStream
     *      |
     *     继承
     *      |
     * BufferedFileInputStream
     *
     *
     *
     * 假如 java io 采用继承的方式实现。
     * @throws IOException
     */
    private void test1() throws IOException {

        // 如果使用继承来实现，似乎只需要一行代码就能解决问题。免去包装步骤
        InputStream bin = new BufferedFileInputStream("/user/wangzheng/test.txt");

        byte[] data = new byte[128];
        while (bin.read(data) != -1) {
            //...
            System.out.println("...");
        }

    }

    /**
     * 继承存在的问题：
     *
     * 如果 InputStream 只有一个子类 FileInputStream 的话，
     * 那我们在 FileInputStream 基础之上，再设计一个孙子类 BufferedFileInputStream，也算是可以接受的，毕竟继承结构还算简单。
     *
     * 但实际上，继承 InputStream 的子类有很多。
     * 我们需要给每一个 InputStream 的子类，再继续派生支持缓存读取的子类。
     * 除了支持缓存读取之外，如果我们还需要对功能进行其他方面的增强，
     * 比如下面的 DataInputStream 类，支持 按照 基本数据类型（int、boolean、long 等）来读取数据。
     *
     *
     * 在这种情况下，如果我们继续按照继承的方式来实现的话，就需要再继续派生出 DataFileInputStream、DataPipedInputStream 等类。
     * 如果我们还需要既支持缓存、又支持按照基本类型读取数据的类，
     * 那就要再继续派生出 BufferedDataFileInputStream、BufferedDataPipedInputStream 等 n 多类。
     * 这还只是附加了两个增强功能，如果我们需要附加更多的增强功能，那就会导致组合爆炸，类继承结构变得无比复杂，代码既不好扩展，也不好维护。
     *
     * 这也是我们在第 10 节中讲的不推荐使用“继承”的原因。
     * @see Main
     *
     * @throws IOException
     */
    private void test2() throws IOException {

        InputStream in = new FileInputStream("/user/wangzheng/test.txt");

        // 功能增强1：缓存
        InputStream bin = new BufferedInputStream(in);

        // 功能增强2：使用 基本数据类型 读取数据
        DataInputStream din = new DataInputStream(bin);
        int data = din.readInt();

    }

}
