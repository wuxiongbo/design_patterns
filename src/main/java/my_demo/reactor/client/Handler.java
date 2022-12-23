package my_demo.reactor.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * <p> 回调函数handler </p>
 * <p>
 * 回调体现在哪里？
 * Acceptor 将 Handler 注册到 selector
 * selector 在收到事件后，selector 又回调 Handler 里面的 业务逻辑
 * <p>
 * <p>
 * 说明：
 * SelectionKey 是个 由 SocketChannel、Selector 组成的 组合对象
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/18
 * </pre>
 */
public class Handler implements Runnable {

    // 依赖注入
    // 中心I/O多路复用器
    private final Selector selector;
    SocketChannel socket;

    // 构造方法初始化
    SelectionKey sk;


    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);

    static final int READING = 0, SENDING = 1;
    int state = READING;


    public Handler(Selector selector, SocketChannel socket) throws IOException {
        this.selector = selector;
        this.socket = socket;


        // 注册socket； 关注 读事件
        // selector.register(socket);
        // 这里，个人觉得有个非常 有趣的点。
        // socket.register(selector) 其实等价于 selector.register(this)
        sk = this.socket.register(selector, SelectionKey.OP_READ);

        // 为key 覆盖 绑定附加对象 Handler。  这行代码，使得 Handler 对象得以保活
        sk.attach(this);


        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            if (sk.isReadable()) {
                read();
            }
            // 客户端将 数据写入到 ByteBuffer 后，被系统监听到。selector 被唤醒。从而进入到这里来。
            else if (sk.isWritable()) {
                doWrite();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read() throws IOException {
        // 将从 管道 读到的数据，写到buffer
        socket.read(input);

        // 执行业务逻辑代码
        process();

        state = SENDING;
        // 关注 读、写事件
        sk.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
    }

    // 将数据 预写入 到 ByteBuffer 缓存
    public void send(String text) throws IOException {
        output.put(text.getBytes());
        output.flip();

        // 关注 读、写事件
        sk = this.socket.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        sk.attach(this);

        state = SENDING;
    }

    // 真正将数据 从 缓存 写入 到 SocketChannel 管道
    private void doWrite() throws IOException {
        SocketChannel sc = (SocketChannel) sk.channel();

        if (output.hasRemaining()) {
            int count = sc.write(output);
            System.out.println("write :" + count + "byte, remaining:" + output.hasRemaining());
            state = READING;
        } else {
            /*取消对写的注册*/
            sk.interestOps(SelectionKey.OP_READ);
        }
    }

    // 处理非IO操作(业务逻辑代码)
    void process() {
        // 处理buffer中的数据
        input.flip();
        // 缓存区中的元素个数
        int remaining = input.remaining();
        byte[] bytes = new byte[remaining];
        // 将缓存区中的数据读取到数组中
        input.get(bytes);


        String msg = new String(bytes);
        System.out.println("客户端 读取 到消息：" + msg);
    }

}
