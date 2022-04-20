package chapter50.demo2;

import chapter50.demo2.component.InputStream;
import chapter50.demo2.component.impl.FileInputStream;
import chapter50.demo2.decorator.impl.BufferedInputStream;
import chapter50.demo2.decorator.impl.DataInputStream;

import java.io.IOException;

/**
 * <p> 客户端 </p>
 *
 * 个人总结：
 *
 *     代理模式，通常指的是 动态代理，毕竟 静态代理太重复，所以很少用
 *     所以，代理模式 与 装饰器模式 的对比 ，就是  动态代理 与 装饰器模式 的 对比
 *     动态代理，通常是一次增强。很少 出现，代理的代理，甚至 代理的代理的代理 这种嵌套代理的情况。
 *     而装饰器模式，则可以嵌套增强。 原始组件被增强后，可以对包装后的 修饰器再次进行包装，修饰增强。
 *
 *     这是这两种模式最大的场景使用区别
 *
 * @see my_demo.decorator.demo1.Client
 * @see my_demo.decorator.demo2.Main
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class Client {
    public static void main(String[] args) throws IOException {

        InputStream in = new FileInputStream("/user/wangzheng/test.txt");

        // 装饰器1，功能增强：缓存
        InputStream bin = new BufferedInputStream(in);

        byte[] data = new byte[128];
        while (bin.read(data) != -1) {
            //...
        }


        // 装饰器2，再次功能增强
        InputStream bin1 = new DataInputStream(in);


    }
}
