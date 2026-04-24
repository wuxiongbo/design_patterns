package my_demo.reactor;

import my_demo.reactor.server.Reactor;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 单线程
        try (ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory())) {
            executorService.execute(new Reactor(2021));
        }

        synchronized (ServerMain.class) {
            System.out.println(ClassLayout.parseInstance(Reactor.class).toPrintable());
            ServerMain.class.wait();
        }
    }
}
