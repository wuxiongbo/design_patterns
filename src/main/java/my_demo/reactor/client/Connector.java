package my_demo.reactor.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * <p>acceptor调度器</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/18
 * </pre>
 */
public class Connector implements Runnable {

    SocketChannel serverSocket;
    Selector selector;

    public Connector(SocketChannel serverSocket, Selector selector) {
        this.serverSocket = serverSocket;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            if(serverSocket.finishConnect()){
                // selector.register(serverSocket);
                Handler handler = new Handler(selector, serverSocket);
                handler.send("43243434243423");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}