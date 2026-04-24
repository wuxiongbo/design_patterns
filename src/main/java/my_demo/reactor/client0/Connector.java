package my_demo.reactor.client0;

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
    private final SocketChannel socket;

    public Connector(Selector selector, SocketChannel serverSocket) {
        this.selector = selector;
        this.socket = serverSocket;
    }

    @Override
    public void run() {
        try {
            // 连接完成
            if (socket.finishConnect()) {

                // 注册socket； selector.register(socket);
                // 这里，个人觉得有个非常 有趣的点。
                // socket.register(selector) 其实等价于 selector.register(this)
                Handler handler = new Handler(selector, socket);

                // 将消息写入到 outputBuffer 缓存。 并告诉 selector 当前管道关注 读、写事件
                handler.send("0000000000000000");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}