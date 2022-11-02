package my_demo.reactor.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * <p>回调函数 handler</p>
 *
 * 回调体现在哪里？
 * Acceptor 将 Handler 注册到 selector
 * selector 在收到事件后，selector 又回调 Handler 里面的 业务逻辑
 *
 *
 * 说明：
 * SelectionKey 是个 由 SocketChannel、Selector 组成的 组合对象
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

        // 用给定的选择器 注册 这个 channel通道。 返回一个 SelectionKey。
        sk = this.socket.register(selector,SelectionKey.OP_READ);

        // 绑定附加对象
        sk.attach(this);

        selector.wakeup();
    }

    @Override
    public void run() {
        try{

            // 读事件
            if (sk.isReadable()) {
                read();
            }
            // 写事件
            else if (sk.isWritable()) {
                send();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void read() throws IOException {
        // 将读到的数据，写到buffer
        socket.read(input);

        // 执行业务逻辑代码
        process();

        state = SENDING;
        // Normally also do first write now
        sk.interestOps(SelectionKey.OP_WRITE|SelectionKey.OP_READ);

    }

    private void send() throws IOException {
        if(output.hasRemaining()){
            int count = socket.write(output);
            System.out.println("write :"+count +"byte, remaining:"+output.hasRemaining());
        }else{
            sk.interestOps(SelectionKey.OP_READ);
            state = READING;
        }
    }


    // 处理非IO操作(业务逻辑代码)
    private void process(){
        // 处理buffer中的数据
        input.flip();
        byte[] bytes = new byte[input.remaining()];
        input.get(bytes);

        String ask = doProcess(new String(bytes));

        output.put(ask.getBytes());
        output.flip();
    }

    protected String doProcess(String msg){
        System.out.println("服务器收到消息：" + msg);
        return "收到";
    }


}
