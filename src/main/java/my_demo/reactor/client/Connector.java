package my_demo.reactor.client;

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
            // 连接完成
            if (socket.finishConnect()) {
                // selector.register(socket);
                Handler handler = new Handler(selector, socket);
                handler.send("43243434243423");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}