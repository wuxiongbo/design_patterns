package my_demo.reactor;

import my_demo.reactor.client2.Reactor;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Client2Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        try (ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory())) {

            executorService.execute(new Reactor("127.0.0.1", 2021));
        }

        synchronized (Client2Main.class) {
            Client2Main.class.wait();
        }
    }
}
