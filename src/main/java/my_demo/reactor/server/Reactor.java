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
 * from, Netty源码之Reactor模式 <a href="https://www.toutiao.com/article/6982760949048476190/">...</a>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/18
 * </pre>
 */
public class Reactor implements Runnable {
    Selector selector;
    ServerSocketChannel serverSocket;

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
        // 绑定附加对象
        sk.attach(new Acceptor(serverSocket, selector));
        /**
         * 还可以使用 SPI provider，来创建selector和serverSocket对象
         SelectorProvider p = SelectorProvider.provider();
         selector = p.openSelector();
         serverSocket = p.openServerSocketChannel();
         */
        System.out.println("client: start select event...");
    }

    @Override
    public void run() {
        try {

            // EventLoop
            while (!Thread.interrupted()) {
                selector.select(1000 * 60);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();

                for (SelectionKey selectedKey : selectedKeys) {
                    // 分发器
                    dispatch(selectedKey);
                }
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
                r.run();
            }
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
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
//
//            System.out.println(ClassLayout.parseInstance(Reactor.class).toPrintable());
//
//            Reactor.class.wait();
//        }
    }
}
