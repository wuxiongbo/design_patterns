package my_demo.reactor.server;

import my_demo.reactor.other.Buffer;
import my_demo.reactor.other.StateRecord;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 处理读写事件
 *
 * <p>回调函数 Handler.run()</p>
 * <p>
 * 回调体现在哪里？
 * Acceptor 将 Handler 注册到 selector
 * selector 在收到事件后，selector 又回调 Handler 里面的 业务逻辑
 * <p>
 * <p>
 * 说明：
 * SelectionKey 是个 由 SocketChannel、Selector 组成的 组合对象
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/18
 * </pre>
 */
public class Handler implements Runnable {

    // 客户端连接
    // SocketChannel ≈ Socket
    private final SocketChannel socketChannel;

    // SelectionKey：事件凭证，表示发生了什么事件；
    // SelectionKey 可以理解为 Channel 的 事件标识；发生事件时， Selector 会返回 SelectionKey。
    // 通过它，判断事件类型
    //   key.isAcceptable();  有新连接
    //   key.isReadable();    有数据可读
    //   key.isWritable();    可以写数据
    private final SelectionKey sk;

    // 状态记录器
    private final StateRecord stateRecord = new StateRecord();
    // 缓存区
    private final Buffer buffer = new Buffer();

    public Handler(Selector selector, SocketChannel socketChannel) throws IOException {

        this.socketChannel = socketChannel;
        // 设置成非阻塞
        this.socketChannel.configureBlocking(false);

        this.sk = registerScAndAttach(selector, socketChannel);

        selector.wakeup();
    }

    private SelectionKey registerScAndAttach(Selector selector, SocketChannel socketChannel) throws ClosedChannelException {

        // 用给定的选择器 注册 这个 channel通道。 返回一个 SelectionKey。
        // 这里，个人觉得有个非常 有趣的点。
        // socketChannel.register(selector) 其实等价于 selector.register(socketChannel)
        SelectionKey sk = socketChannel.register(selector, SelectionKey.OP_READ);

        // 覆盖 附加对象 Handler 到  key;
        // 这行代码 使 Handler 对象 得以保活
        sk.attach(this);

        return sk;
    }


    @Override
    public void run() {
        try {
            // 读事件
            if (sk.isReadable()) {
                read();
            }
            // 写事件
            else if (sk.isWritable()) {
                // 客户端将 数据写入到 ByteBuffer 后，被系统监听到。selector 被唤醒。从而进入到这里来。
                send();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void read() throws IOException {

        // 从 Channel 读数据，写入 到  处于 “写模式” 中的 buffer
        socketChannel.read(buffer.inputBuffer);

        // 执行业务逻辑代码
        process();

        // 感兴趣的操作
        sk.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);

        stateRecord.state = StateRecord.SENDING;
    }

    private void send() throws IOException {

        // 当前位置和限制之间是否有任何元素。
        // true，此缓冲区中至少剩一个元素
        if (buffer.outputBuffer.hasRemaining()) {

            // 从 处于 “读模式” 的 buffer 中 读数据，通过 Channel 将读到的数据写出去
            int count = socketChannel.write(buffer.outputBuffer);

            System.out.println("write :" + count + "byte, remaining:" + buffer.outputBuffer.hasRemaining());
        } else {

            // 感兴趣的操作
            sk.interestOps(SelectionKey.OP_READ);

            stateRecord.state = StateRecord.READING;
        }
    }

    // 处理非IO操作：
    // 1）二进制数据 转为 可读业务数据
    // 2）组装待返回的业务数据(业务逻辑代码)
    private void process() {

        // 它的意思不是“翻转数据”，而是：从 "写模式" 切换到 "读模式" 。
        buffer.inputBuffer.flip();

        // 缓存区中的元素个数
        int remaining = buffer.inputBuffer.remaining();
        byte[] inBytes = new byte[remaining];
        // 将 "读模式" 缓存区 中的数据，读取到 byte数组 中
        buffer.inputBuffer.get(inBytes);


        // 消费 接收到的消息， 生成 回复的消息
        byte[] outBytes = doProcessMsg(new String(inBytes));
        // 数据写入 处于 "写模式" 中的 缓存区
        buffer.outputBuffer.put(outBytes);
        // 写完后，将 buffer 从 "写模式" 切换到 "读模式"
        buffer.outputBuffer.flip();

    }

    protected byte[]  doProcessMsg(String msg) {
        System.out.println("服务器收到消息：" + msg);
        return "收到".getBytes();
    }

    public int getState() {
        return stateRecord.state;
    }
}
