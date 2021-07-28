package chapter21.demo3.v2;

import chapter21.demo3.v1.util.User;

/**
 * <p>  代码执行重复 问题的优化 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class UserRepo {
    public boolean checkIfUserExisted(String email, String password) {
        //...query db to check if email&password exists
        return false;
    }

    public User getUserByEmail(String email) {
        //...query db to get user by email...
        return null;
    }
}
