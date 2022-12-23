package my_demo.reactor.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;
import java.util.concurrent.*;

/**
 * <p>Reactor</p>
 * <p>
 * <p>
 * from
 * <a href="https://www.toutiao.com/article/6982760949048476190/"> Netty源码之Reactor模式 </a>
 * <a href = "https://blog.csdn.net/weixin_44471490/article/details/114606481"> Reactor 线程模型 </a>
 *
 *
 *
 * 命令式编程   HOW
 *   一步一步告诉计算机怎么做。 如：if/else/for等控制语句、表达式、数据变量的操作、赋值等指令
 *
 * 声明式编程   WHAT
 *   告诉计算机应该做什么想要什么结果，但不关注具体要怎么做。
 *   如： sql     SELECT * FROM collection WHERE num > 5
 *       spring  事务注解
 *
 * 函数式编程
 *   “函数第一位”，即，函数可以出现在任何地方，比如你可以把  函数作为参数  传递给  另一个函数，不仅如此，你还可以 将函数作为 返回值。
 *
 * 响应式编程
 *   观察者模式、Reactor设计模式。
 *   不主动调用某个请求的API，而是通过 事先 注册对应 接口，实现未来某个时刻，通过事件 触发执行该接口
 *
 *   excel 计算函数。将计算函数提前写好。 输入数据流，动态变动结果
 *
 *
 * 面向对象编程
 *  从问题域出发，将封装、继承、多态的语言特性映射到现实世界
 *  对象 作为程序的基本组成单元
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/18
 * </pre>
 */
public class Reactor implements Runnable {
    // 中心I/O多路复用器
    private final Selector selector;
    private final ServerSocketChannel serverSocket;

    public Reactor(int port) throws IOException {
        // 创建serverSocket对象
        serverSocket = ServerSocketChannel.open();
        // 绑定端口
        serverSocket.socket().bind(new InetSocketAddress(port));
        // 配置非阻塞
        serverSocket.configureBlocking(false);
        // 创建selector对象
        selector = Selector.open();


        // serverSocket注册到selector上，帮忙监听accept事件。
        // 这里，个人觉得有个非常 有趣的点。
        // serverSocket.register(selector) 其实等价于 selector.register(this)
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        // 绑定附加对象 Acceptor 到 key
        sk.attach(new Acceptor(serverSocket, selector));

//         还可以使用 SPI provider，来创建 selector 和 serverSocket对象。如下：
//         SelectorProvider p = SelectorProvider.provider();
//         selector = p.openSelector();
//         serverSocket = p.openServerSocketChannel();

        System.out.println("client: start select event...");
    }


    // 单线程跑即可
    @Override
    public void run() {
        try {

            // EventLoop
            while (!Thread.interrupted()) {

                // 获取 所有的就绪事件
                selector.select(1000 * 60);

                // 获取已就绪的事件列表
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                for (SelectionKey selectedKey : selectedKeys) {
                    // 事件分发器
                    dispatch(selectedKey);
                }
                // 清空事件列表
                selectedKeys.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void dispatch(SelectionKey key) {
        if (key.isValid()) {
            // 获取管道上的附加对象。 Acceptor、Handler
            Runnable r = (Runnable) key.attachment();
            if (r != null) {
                // 这里可以改造成多线程
                r.run();
            }
        }
    }


    /**
     * 入口 {@link Reactor#run()}
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // 单线程
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory());

        executorService.execute(new Reactor(2021));


        Reactor.class.wait();

//        synchronized (Reactor.class) {
//            System.out.println(ClassLayout.parseInstance(Reactor.class).toPrintable());
//            Reactor.class.wait();
//        }

    }
}
