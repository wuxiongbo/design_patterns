package my_demo.reactor.client1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

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
    private final SocketChannel socketChannel;


    public Reactor(String host, int port) throws IOException {
        this.port = port;
        this.host = host;

        // 创建selector对象
        selector = Selector.open();
        // 初始化serverSocket对象
        socketChannel = openSocketChannel();
        // 将 socketChannel 注册到 selector
        registerScAndAttach();
    }


    private SocketChannel openSocketChannel() throws IOException {
        // 初始化 serverSocket 对象
        SocketChannel socket = SocketChannel.open();
        // 配置非阻塞
        socket.configureBlocking(false);
        return socket;
    }

    private void registerScAndAttach() throws ClosedChannelException {
        // 将 socketChannel 注册到 selector
        SelectionKey sk = socketChannel.register(selector, SelectionKey.OP_CONNECT);
        // 绑定  附加对象 Connector 到 key
        sk.attach(new my_demo.reactor.client2.Connector(selector, socketChannel));
        System.out.println("client2: start select event...");
    }

    @Override
    public void run() {
        try {
            socketChannel.connect(new InetSocketAddress(host, port));
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
}
