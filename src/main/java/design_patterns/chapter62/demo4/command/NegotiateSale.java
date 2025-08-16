package design_patterns.chapter62.demo4.command;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 3 谈判
 * @author Xander Wu
 * @date 2022/12/9 13:40
 */
public class NegotiateSale implements Command {

    @Override
    public boolean execute(Context context) throws Exception {
        System.out.println("This is Negotiate");


        return false;
    }
}