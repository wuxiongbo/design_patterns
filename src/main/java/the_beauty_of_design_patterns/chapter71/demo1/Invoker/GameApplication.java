package the_beauty_of_design_patterns.chapter71.demo1.Invoker;

import the_beauty_of_design_patterns.chapter71.demo1.command.Command;
import the_beauty_of_design_patterns.chapter71.demo1.command.concretecommand.ArchiveCommand;
import the_beauty_of_design_patterns.chapter71.demo1.command.concretecommand.GotDiamondCommand;
import the_beauty_of_design_patterns.chapter71.demo1.command.concretecommand.GotStartCommand;
import the_beauty_of_design_patterns.chapter71.demo1.command.concretecommand.HitObstacleCommand;
import the_beauty_of_design_patterns.chapter71.dependence.Event;
import the_beauty_of_design_patterns.chapter71.dependence.Request;

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

    // 最大处理请求指令数
    private static final int MAX_HANDLED_REQ_COUNT_PER_LOOP = 100;

    // Invoker 持有 部分符合事件的 命令对象
    private Queue<Command> queue = new LinkedList<>();


    public void mainloop() {

        while (true) {

            //省略从 epoll 或者 select 中获取数据，并封装成Request的逻辑，
            //注意设置超时时间，接收请求超时，就继续下面的逻辑处理。
            List<Request> requests = new ArrayList<>();


            // 根据 请求的事件 设置 相应的 命令对象
            setCommand(requests);


            // 执行命令
            call();

        }
    }


    private void setCommand(List<Request> requests){
        for (Request request : requests) {
            Event event = request.getEvent();

            Command command = null;

            // 根据请求事件，添加不同的命令。
            if (event==Event.GOT_DIAMOND) {
                command = new GotDiamondCommand(/*receiver 数据*/);
            } else if (event==Event.GOT_STAR) {
                command = new GotStartCommand(/*receiver 数据*/);
            } else if (event==Event.HIT_OBSTACLE) {
                command = new HitObstacleCommand(/*receiver 数据*/);
            } else if (event==Event.ARCHIVE) {
                command = new ArchiveCommand(/*receiver 数据*/);
            } // ...一堆else if...

            queue.add(command);
        }
    }


    private void call(){

        // 遍历 执行命令
        for (int handledCount = 0; handledCount < MAX_HANDLED_REQ_COUNT_PER_LOOP; handledCount++) {

            if (queue.isEmpty()) {
                break;
            }

            Command command = queue.poll();

            // 执行指令
            command.execute();

        }

    }


}
