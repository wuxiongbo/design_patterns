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

    Selector selector;
    SocketChannel socket;
    SelectionKey sk;

    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);

    static final int READING = 0, SENDING = 1;
    int state = READING;


    public Handler(Selector selector, SocketChannel socket) throws IOException {
        this.selector = selector;
        this.socket = socket;
        this.socket.configureBlocking(false);

        // 注册socket； selector.register(socket);
        // 这里，个人觉得有个非常 有趣的点。
        // socket.register(selector) 其实等价于 selector.register(this)
        sk = this.socket.register(selector, SelectionKey.OP_READ);

        // 绑定附加对象
        sk.attach(this);

        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            if (sk.isReadable()) {
                read();
            } else if (sk.isWritable()) {
                doWrite();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read() throws IOException {
        // 将读到的数据，写到buffer
        socket.read(input);
        // 执行业务逻辑代码
        process();
        state = SENDING;
        sk.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
    }

    // 预写入 到 ByteBuffer
    public void send(String text) throws IOException {
        output.put(text.getBytes());
        output.flip();
        sk = this.socket.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        sk.attach(this);
        state = SENDING;
    }

    // 真正写入 到 SocketChannel
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
        byte[] bytes = new byte[input.remaining()];
        input.get(bytes);
        String msg = new String(bytes);
        System.out.println("客户端收到消息：" + msg);
    }
}
