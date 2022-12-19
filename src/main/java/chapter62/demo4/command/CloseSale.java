package chapter62.demo4.command;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 5 交付
 * @author Xander Wu
 * @date 2022/12/9 13:39
 */
public class CloseSale  implements Command {

    @Override
    public boolean execute(Context context) throws Exception {
        System.out.println("Thanks," + context.get("userName") + ",enjoying your driving");
        return false;
    }
}