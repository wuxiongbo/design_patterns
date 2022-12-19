package chapter62.demo4.command;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 2 预约试驾
 * @author Xander Wu
 * @date 2022/12/9 13:39
 */
public class TestDriver implements Command {

    @Override
    public boolean execute(Context context) throws Exception {
        System.out.println("Test driver begin....");



        return false;
    }
}