package chapter21.demo3.v2;

import chapter21.demo3.v1.util.EmailValidation;
import chapter21.demo3.v1.util.PasswordValidation;
import chapter21.demo3.v1.util.User;

/**
 * <p> 代码执行重复 问题的优化</p>
 *
 * 优化：移除“重复执行”的代码
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class UserService {
    private UserRepo userRepo;//通过依赖注入或者IOC框架注入

    public User login(String email, String password) {

        // 只校验一次 email 和 password，只查询一次数据库。不在查询user是否存在。
        if (!EmailValidation.validate(email)) {
            // ... throw InvalidEmailException...
        }
        if (!PasswordValidation.validate(password)) {
            // ... throw InvalidPasswordException...
        }

        User user = userRepo.getUserByEmail(email);

        // 校验密码正确性
        if (user == null || !password.equals(user.getPassword()) ) {
            // ... throw AuthenticationFailureException...
        }

        return user;
    }
}
