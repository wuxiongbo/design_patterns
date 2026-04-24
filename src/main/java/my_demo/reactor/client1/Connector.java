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

    private final Selector selector;
    private final SocketChannel socketChannel;

    public Connector(Selector selector, SocketChannel serverSocket) {
        this.socketChannel = serverSocket;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            if (socketChannel.finishConnect()) {
                Handler handler = new Handler(selector, socketChannel);
                handler.send("11111111111111111");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}