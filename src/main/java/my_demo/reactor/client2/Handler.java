package my_demo.reactor.client2;

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
        sk = this.socket.register(selector, SelectionKey.OP_READ);

        sk.attach(this);

        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            if (sk.isReadable()) {
                read();
            }
            else if (sk.isWritable()) {
                doWrite();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read() throws IOException {
        socket.read(input);

        process();

        state = SENDING;
        sk.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);
    }

    public void send(String text) throws IOException {
        output.put(text.getBytes());
        output.flip();

        // 关注 读、写事件
        sk = this.socket.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        sk.attach(this);
        state = SENDING;
    }

    private void doWrite() throws IOException {
        SocketChannel sc = (SocketChannel) sk.channel();

        if (output.hasRemaining()) {
            int count = sc.write(output);
            System.out.println("2write :" + count + "byte, remaining:" + output.hasRemaining());
            state = READING;
        } else {
            /*取消对写的注册*/
            sk.interestOps(SelectionKey.OP_READ);
        }
    }

    void process() {
        // 处理buffer中的数据
        input.flip();
        // 缓存区中的元素个数
        int remaining = input.remaining();
        byte[] bytes = new byte[remaining];
        // 将缓存区中的数据读取到数组中
        input.get(bytes);


        String msg = new String(bytes);
        System.out.println("客户端2 读取 到消息：" + msg);
    }

}
