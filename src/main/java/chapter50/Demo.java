package chapter50;

import chapter50.dependence.BufferedFileInputStream;

import java.io.*;

/**
 * <p>io流</p>
 *
 * 示例：打开文件 test.txt，从中读取数据
 *
 *
 *
 * 装饰器模式相对于简单的组合关系，还有两个比较特殊的地方。
 *
 * 第一个比较特殊的地方是：
 *    装饰器类(BufferedInputStream)和原始类(FileInputStream)继承同样的父类(InputStream)，
 *    这样，我们可以对原始类“嵌套”多个 装饰器类。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/9/13
 * </pre>
 */
public class Demo {

    public void test() throws IOException {

        // InputStream 是一个抽象类，FileInputStream 是专门用来读取文件流的子类。
        InputStream in = new FileInputStream("/user/wangzheng/test.txt");

        // BufferedInputStream 是一个支持带缓存功能的数据读取类，可以提高数据读取的效率。
        InputStream bin = new BufferedInputStream(in);
        byte[] data = new byte[128];
        while (bin.read(data) != -1) {
            //...
        }

    }

    /**
     * 假如 java io 采用继承的方式实现。
     * @throws IOException
     */
    private void test1() throws IOException {

        InputStream bin = new BufferedFileInputStream("/user/wangzheng/test.txt");

        byte[] data = new byte[128];
        while (bin.read(data) != -1) {
            //...
        }

    }

    private void test2() throws IOException {

        InputStream in = new FileInputStream("/user/wangzheng/test.txt");
        InputStream bin = new BufferedInputStream(in);
        DataInputStream din = new DataInputStream(bin);
        int data = din.readInt();

    }
}
