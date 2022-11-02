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
 * from, Netty源码之Reactor模式 https://www.toutiao.com/article/6982760949048476190/
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



        // serverSocket注册到selector上，帮忙监听 CONNECT 事件。  selector.register(this)
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

        System.out.println("client: start select event...");
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
                selector.select(1000*60);
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
