package my_demo.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.statemachine.StateMachine;

/**
 * @author bear
 * @date 2024/2/21 23:21
 * @description
 */
@Component
public class CommandLineRunnerDemo implements CommandLineRunner {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.sendEvent(Events.E1);
        stateMachine.sendEvent(Events.E2);
    }


}
