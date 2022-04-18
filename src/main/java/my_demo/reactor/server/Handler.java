package my_demo.reactor.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * <p>回调函数handler</p>
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

        sk = this.socket.register(selector,SelectionKey.OP_READ);

        // 绑定附加对象
        sk.attach(this);

        selector.wakeup();
    }

    @Override
    public void run() {
        try{
            if (sk.isReadable()) {
                read();
            } else if (sk.isWritable()) {
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
    void process(){
        // 处理buffer中的数据
        input.flip();
        byte[] bytes = new byte[input.remaining()];
        input.get(bytes);
        String msg = new String(bytes);
        System.out.println("服务器收到消息：" + msg);

        output.put(msg.getBytes());
        output.flip();
    }
}
