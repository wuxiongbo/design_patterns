package my_demo.reactor.server;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * <p>acceptor调度器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/18
 * </pre>
 */
public class Acceptor implements Runnable {

    ServerSocketChannel serverSocket;
    Selector selector;

    public Acceptor(ServerSocketChannel serverSocket,Selector selector) {
        this.serverSocket = serverSocket;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            // 分配一个 Channel。
            SocketChannel socket = this.serverSocket.accept();
            if (socket != null) {
                // 仅仅需要调用构造方法。 绑定 附加对象 以及感兴趣的事件
                // 构造方法中，此对象会绑定到相应的channel，从而使该对象保活
                new Handler(selector,socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}