package chapter62.demo4.command;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

/**
 * 1 获取客户信息
 * @author Xander Wu
 * @date 2022/12/9 13:39
 */
public class GetUserInfo implements Command {

    @Override
    public boolean execute(Context context) throws Exception {
        System.out.println("Get user info begin");

        context.put("userName", "Tom");

        return false;
    }
}