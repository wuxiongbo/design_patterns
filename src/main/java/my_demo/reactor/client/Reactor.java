package my_demo.reactor.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    int port;
    String host;
    Selector selector;
    SocketChannel socket;

    public Reactor(String host, int port) throws IOException {
        this.port = port;
        this.host = host;


        // 初始化serverSocket对象
        socket = SocketChannel.open();
        // 配置非阻塞
        socket.configureBlocking(false);
        // 创建selector对象
        selector = Selector.open();


        // 将serverSocket注册到selector上，让其帮忙监听 CONNECT 事件。  以下两种写法
        // selector.register(this)
//        SelectionKey sk = serverSocket.register(selector, 0);
//        sk.interestOps(SelectionKey.OP_CONNECT);
        SelectionKey sk = socket.register(selector, SelectionKey.OP_CONNECT);


        // 绑定附加对象
        sk.attach(new Connector(socket, selector));


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
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            while (!Thread.interrupted()) {

                // 读取就绪事件
                selector.select(1000 * 60);

                // 获取已就绪的事件列表
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                for (SelectionKey selectedKey : selectedKeys) {
                    // 事件分发
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
                r.run();
            }
        }
    }




    public static void main(String[] args) throws IOException, InterruptedException {
        // 单线程
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory());

        executorService.execute(new Reactor("127.0.0.1", 2021));

        synchronized (Reactor.class) {
            Reactor.class.wait();
        }

    }
}
