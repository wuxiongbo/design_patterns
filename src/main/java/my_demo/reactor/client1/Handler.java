package my_demo.reactor.client1;

import my_demo.reactor.other.Buffer;
import my_demo.reactor.other.StateRecord;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
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

    private final Selector selector;
    private final SocketChannel socketChannel;

    private SelectionKey sk;

    // 状态记录器
    private final StateRecord stateRecord = new StateRecord();
    // 缓存区
    private final Buffer buffer = new Buffer();

    public Handler(Selector selector, SocketChannel socketChannel) throws IOException {

        this.selector = selector;

        this.socketChannel = socketChannel;
        this.socketChannel.configureBlocking(false);

        this.sk = registerScAndAttach(selector);

        selector.wakeup();
    }

    private SelectionKey registerScAndAttach(Selector selector) throws ClosedChannelException {
        SelectionKey sk =  this.socketChannel.register(selector, SelectionKey.OP_READ);
        sk.attach(this);
        return sk;
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
        int count = socketChannel.read(buffer.inputBuffer);
        if (count == -1) {
            sk.cancel();
            socketChannel.close();
            return;
        }
        if (count == 0) {
            return;
        }
        process();
        stateRecord.state = StateRecord.SENDING;
        sk.interestOps(SelectionKey.OP_READ);
    }

    public void send(String text) throws IOException {
        buffer.outputBuffer.clear();
        buffer.outputBuffer.put(text.getBytes());
        buffer.outputBuffer.flip();

        // 已经注册过，只需要更新当前 SelectionKey 关注的事件
        sk.interestOps(SelectionKey.OP_WRITE | SelectionKey.OP_READ);

        stateRecord.state = StateRecord.SENDING;
    }

    private void doWrite() throws IOException {
        SocketChannel sc = (SocketChannel) sk.channel();
        if (buffer.outputBuffer.hasRemaining()) {
            int count = sc.write(buffer.outputBuffer);
            System.out.println("1write :" + count + "byte, remaining:" + buffer.outputBuffer.hasRemaining());

            if (!buffer.outputBuffer.hasRemaining()) {
                buffer.outputBuffer.clear();
                buffer.outputBuffer.flip();
                sk.interestOps(SelectionKey.OP_READ);
                stateRecord.state = StateRecord.READING;
            }
        } else {
            buffer.outputBuffer.clear();
            buffer.outputBuffer.flip();
            sk.interestOps(SelectionKey.OP_READ);
        }
    }

    void process() {
        buffer.inputBuffer.flip();

        byte[] bytes = new byte[buffer.inputBuffer.remaining()];
        buffer.inputBuffer.get(bytes);
        buffer.inputBuffer.clear();

        String msg = new String(bytes);
        System.out.println("客户端1 读取 到消息：" + msg);
    }

    public int getState() {
        return stateRecord.state;
    }
}
