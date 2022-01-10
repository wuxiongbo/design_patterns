package chapter71;

import chapter71.concretecommand.ArchiveCommand;
import chapter71.concretecommand.GotDiamondCommand;
import chapter71.concretecommand.GotStartCommand;
import chapter71.concretecommand.HitObstacleCommand;
import chapter71.dependence.Event;
import chapter71.dependence.Request;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <p> Invoker </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class GameApplication {
    private static final int MAX_HANDLED_REQ_COUNT_PER_LOOP = 100;

    // Invoker 持有 命令对象
    private Queue<Command> queue = new LinkedList<>();


    public void mainloop() {

        while (true) {

            //省略从 epoll 或者 select 中获取数据，并封装成Request的逻辑，
            //注意设置超时时间，接收请求超时，就继续下面的逻辑处理。
            List<Request> requests = new ArrayList<>();


            // 设置指令对象
            setCommand(requests);


            // 调用指令对象
            call();

        }
    }


    private void setCommand(List<Request> requests){
        for (Request request : requests) {
            Event event = request.getEvent();
            Command command = null;
            if (event.equals(Event.GOT_DIAMOND)) {
                command = new GotDiamondCommand(/*数据*/);
            } else if (event.equals(Event.GOT_STAR)) {
                command = new GotStartCommand(/*数据*/);
            } else if (event.equals(Event.HIT_OBSTACLE)) {
                command = new HitObstacleCommand(/*数据*/);
            } else if (event.equals(Event.ARCHIVE)) {
                command = new ArchiveCommand(/*数据*/);
            } // ...一堆else if...

            queue.add(command);
        }
    }


    private void call(){
        int handledCount = 0;
        while (handledCount < MAX_HANDLED_REQ_COUNT_PER_LOOP) {
            if (queue.isEmpty()) {
                break;
            }
            Command command = queue.poll();

            // 执行指令
            command.execute();

            handledCount++;
        }
    }


}
