package my_demo.reactor.client0;

import my_demo.reactor.other.Buffer;
import my_demo.reactor.other.StateRecord;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.List;

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
    private final SocketChannel socketChannel;

    // 通道
    private SelectionKey sk;

    // 状态记录器
    private final StateRecord stateRecord = new StateRecord();
    // 缓存区
    private final Buffer buffer = new Buffer();

    public Handler(Selector selector, SocketChannel socketChannel) throws IOException {

        this.selector = selector;

        this.socketChannel = socketChannel;
        this.socketChannel.configureBlocking(false);

        // 绑定 附加对象 Handler
        this.sk = registerScAndAttach(selector);

        selector.wakeup();
    }

    private SelectionKey registerScAndAttach(Selector selector) throws ClosedChannelException {
        // 1) 注册 socketChannel 到 selector； 关注 读事件
        // selector.register(socket);
        // 这里，个人觉得有个非常 有趣的点。
        // socketChannel.register(selector,ops) 其实等价于 selector.register(socketChannel,ops)
        SelectionKey sk =  this.socketChannel.register(selector, SelectionKey.OP_READ);
        // 2) 为 SelectionKey 绑定 附加对象 Handler。 这行代码的目的，是为了 使得 Handler 对象得以保活
        sk.attach(this);
        return sk;
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

    private void read() throws IOException {
        // 将从 管道 读到的数据，写到 处于 “写模式” 的 buffer 中
        socketChannel.read(buffer.inputBuffer);
        // 切换 buffer 到 "读模式"
        buffer.inputBuffer.flip();

        // 执行业务逻辑代码
        processMsg();

        // 关注 读、写事件
        sk.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);

        stateRecord.state = StateRecord.SENDING;
    }

    // 将数据 预写入 到 ByteBuffer 缓存
    public void send(String text) throws IOException {
        // 数据写入 处于 "写模式" 中的 buffer 缓存区
        buffer.outputBuffer.put(text.getBytes());
        // 切换 buffer 到 "读模式"
        buffer.outputBuffer.flip();

        // 已经注册过，只需要更新当前 SelectionKey 关注的事件
        sk.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);

        stateRecord.state = StateRecord.SENDING;
    }

    // 真正将数据 从 缓存 写入 到 SocketChannel 管道
    private void doWrite() throws IOException {

        try (SocketChannel socketChannel = (SocketChannel) sk.channel()) {

            if (buffer.outputBuffer.hasRemaining()) {
                // 从 处于 “读模式” 的 buffer 中 读数据，通过 Channel 将 从 buffer中 读到的数据 写出去
                int count = socketChannel.write(buffer.outputBuffer);
                System.out.println("write :" + count + "byte, remaining:" + buffer.outputBuffer.hasRemaining());

                stateRecord.state = StateRecord.READING;
            } else {
                /*取消对写的注册*/
                sk.interestOps(SelectionKey.OP_READ);
            }
        }
    }

    // 处理非IO操作(业务逻辑代码)
    void processMsg() {
        // buffer缓存区中，元素的个数
        byte[] bytes = new byte[buffer.inputBuffer.remaining()];
        // 将 处于 “读模式” 的 缓存区 中的数据， 读取到 byte数组 中
        buffer.inputBuffer.get(bytes);

        // 模拟业务逻辑代码
        String msg = new String(bytes);
        System.out.println("客户端 读取 到消息：" + msg);
    }


    public int getState() {
        return stateRecord.state;
    }

}
