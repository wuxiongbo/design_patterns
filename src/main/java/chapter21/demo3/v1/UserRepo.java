package chapter21.demo3.v1;

import chapter21.demo3.v1.util.EmailValidation;
import chapter21.demo3.v1.util.PasswordValidation;
import chapter21.demo3.v1.util.User;

/**
 * <p> 代码执行重复 </p>
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class UserRepo {

    public boolean checkIfUserExisted(String email, String password) {
        if (!EmailValidation.validate(email)) {
            // ... throw InvalidEmailException...
        }

        if (!PasswordValidation.validate(password)) {
            // ... throw InvalidPasswordException...
        }

        //...query db to check if email&password exists...
        // 查一次数据库校验 user是否存在。逻辑省略...

        return false;
    }

    public User getUserByEmail(String email) {
        if (!EmailValidation.validate(email)) {
            // ... throw InvalidEmailException...
        }
        //...query db to get user by email...

        return null;
    }
}
