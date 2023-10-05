package the_beauty_of_design_patterns.chapter62.demo4.command;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 4 支付
 * @author Xander Wu
 * @date 2022/12/9 13:39
 */
public class ArrangeFinancing implements Command {

    @Override
    public boolean execute(Context context) throws Exception {

        System.out.println("This is arranging financing");

        return false;
    }
}