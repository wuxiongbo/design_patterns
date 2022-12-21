package my_demo.reactor.client1;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * <p> Connector 调度器 </p>
 * 注册读写事件
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/18
 * </pre>
 */
public class Connector implements Runnable {

    SocketChannel socket;
    Selector selector;

    public Connector(SocketChannel serverSocket, Selector selector) {
        this.socket = serverSocket;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            if (socket.finishConnect()) {
                Handler handler = new Handler(selector, socket);
                handler.send("11111111111111111");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}