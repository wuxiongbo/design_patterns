package chapter41.demo1.v2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class Logger {

    private FileWriter writer;

    public Logger()  {
        try {
            File file = new File("/Users/wangzheng/log.txt");
            writer = new FileWriter(file, true);      //true表示追加写入
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 分布式锁：
     *    除了使用类级别锁之外，实际上，解决资源竞争问题的办法还有很多，分布式锁是最常听到的一种解决方案。
     *    不过，实现一个安全可靠、无 bug、高性能的分布式锁，并不是件容易的事情。
     *
     * 并发队列：
     *     除此之外，并发队列（比如 Java 中的 BlockingQueue）也可以解决这个问题：
     *        多个线程同时往并发队列里写日志，一个单独的线程负责将并发队列中的数据，写入到日志文件。
     *     这种方式实现起来也稍微有点复杂。
     *
     * 单例模式：
     *     相对于这两种解决方案，单例模式的解决思路就简单一些了。
     *
     * @param message
     */
    public void log(String message){
        try {

            // 给 log() 函数加互斥锁
//            synchronized(this) {       // 对象级别的锁

//            此外，FileWriter 本身就是线程安全的，它的内部实现中本身就加了对象级别的锁，
//            因此，在外层调用 write() 函数的时候，再加对象级别的锁实际上是多此一举

            synchronized(Logger.class) { // 类级别的锁
                writer.write(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
