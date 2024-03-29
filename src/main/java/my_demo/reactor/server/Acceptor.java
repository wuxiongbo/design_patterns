package my_demo.reactor.server;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * <p>acceptor 调度器</p>
 * 读写事件
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/18
 * </pre>
 */
public class Acceptor implements Runnable {

    ServerSocketChannel serverSocket;
    Selector selector;

    public Acceptor(ServerSocketChannel serverSocket, Selector selector) {
        this.serverSocket = serverSocket;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            // 分配一个 Channel。
            SocketChannel socket = this.serverSocket.accept();
            if (socket != null) {
                // 仅仅需要调用构造方法。主要做的事情是： 1）绑定 附加对象 2）注册感兴趣的事件
                // 构造方法中，此对象会绑定到相应的channel，从而使该对象保活
                // 注册socket； selector.register(socket);
                // 这里，个人觉得有个非常 有趣的点。
                // socket.register(selector) 其实等价于 selector.register(this)
                new Handler(selector, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}