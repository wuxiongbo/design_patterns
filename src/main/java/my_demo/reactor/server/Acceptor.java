package my_demo.reactor.server;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * <p>acceptor 调度器</p>
 * 接收 读写事件
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/18
 * </pre>
 */
public class Acceptor implements Runnable {

    private final Selector selector;
    // 服务端监听连接
    // ServerSocketChannel ≈ ServerSocket
    private final ServerSocketChannel serverSocketChannel;

    public Acceptor(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void run() {
        try {

            // 1) 分配一个 socketChannel
            SocketChannel socketChannel = createSocketChannel();

            if (socketChannel != null) {
                //
                // 仅仅需要调用构造方法。主要做的事情是： 1）绑定 附加对象 2）注册感兴趣的事件
                // 构造方法中，此 对象 会绑定到相应的 channel，从而使该对象 保活
                // 注册socket； selector.register(socket);

                // 这里，个人觉得有个非常 有趣的点。
                // socketChannel.register(selector)  其实等价于 selector.register(socketChannel)
                new Handler(selector, socketChannel);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SocketChannel createSocketChannel() throws IOException {
        return this.serverSocketChannel.accept();
    }
}