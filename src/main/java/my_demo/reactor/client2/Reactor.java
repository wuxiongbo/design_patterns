package my_demo.reactor.client2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
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

    private final int port;
    private final String host;

    private final Selector selector;
    private final SocketChannel socket;


    public Reactor(String host, int port) throws IOException {
        this.port = port;
        this.host = host;


        // 初始化serverSocket对象
        socket = SocketChannel.open();
        // 配置非阻塞
        socket.configureBlocking(false);
        // 创建selector对象
        selector = Selector.open();
        SelectionKey sk = socket.register(selector, SelectionKey.OP_CONNECT);
        sk.attach(new Connector(socket, selector));
        System.out.println("client2: start select event...");
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
                selector.select(1000 * 60);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                for (SelectionKey selectedKey : selectedKeys) {
                    dispatch(selectedKey);
                }
                selectedKeys.clear();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@link Connector#run()}
     * {@link Handler#run()}
     * @param key
     */
    private void dispatch(SelectionKey key) {
        if (key.isValid()) {
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

        executorService.execute(new Reactor("127.0.0.1", 2021));

        synchronized (Reactor.class) {
            Reactor.class.wait();
        }

    }
}
