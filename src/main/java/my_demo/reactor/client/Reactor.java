package my_demo.reactor.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>Reactor</p>
 *
 *
 * from  https://developer.51cto.com/article/671346.html
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
    SocketChannel serverSocket;

    public Reactor(String host,int port) throws IOException {
        this.port = port;
        this.host = host;
        // 创建serverSocket对象
        serverSocket = SocketChannel.open();
        // 配置非阻塞
        serverSocket.configureBlocking(false);
        // 创建selector对象
        selector = Selector.open();

        // serverSocket注册到selector上，帮忙监听 CONNECT 事件。
        SelectionKey sk = serverSocket.register(selector, 0);

        sk.interestOps(SelectionKey.OP_CONNECT);
        // 绑定附加对象
        sk.attach(new Connector(serverSocket,selector));
        /**
         * 还可以使用 SPI provider，来创建selector和serverSocket对象
         SelectorProvider p = SelectorProvider.provider();
         selector = p.openSelector();
         serverSocket = p.openServerSocketChannel();
         */
    }

    @Override
    public void run() {
        try {
            serverSocket.connect(new InetSocketAddress(host,port));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (!Thread.interrupted()) {
                System.out.println("start select event...");
                selector.select(1000*60);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                while (it.hasNext()) {
                    dispatch(it.next());
                }
                selectedKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey key) {
        if(key.isValid()){
            // 获取管道上的附加对象。 Acceptor、Handler
            Runnable r = (Runnable) key.attachment();
            if (r != null) {
                r.run();
            }
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        Thread thread = new Thread(new Reactor("127.0.0.1",2021));
        thread.start();
        synchronized (Reactor.class) {
            Reactor.class.wait();
        }
    }
}
