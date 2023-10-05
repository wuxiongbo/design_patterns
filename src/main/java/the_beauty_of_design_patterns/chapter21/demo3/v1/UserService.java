package the_beauty_of_design_patterns.chapter21.demo3.v1;


import the_beauty_of_design_patterns.chapter21.demo3.dependence.User;

/**
 * <p> 代码执行重复 </p>
 *
 * UserService 中 login() 函数用来校验用户登录是否成功。
 * 如果失败，就返回异常；
 * 如果成功，就返回用户信息。
 *
 * 重复执行一：
 * email 的校验逻辑 被执行了两次。
 * 一次是在调用 checkIfUserExisted() 函数的时候，另一次是调用 getUserByEmail() 函数的时候。
 *
 * 重复执行二：
 * 实际上，login() 函数并不需要调用 checkIfUserExisted() 函数，只需要调用一次 getUserByEmail() 函数，
 * 从数据库中获取到用户的 email、password 等信息，然后跟用户输入的 email、password 信息做对比，依次判断是否登录成功。
 *
 *
 * 实际上，这样的优化是很有必要的。
 * 因为 checkIfUserExisted() 函数和 getUserByEmail() 函数都需要查询数据库，而数据库这类的 I/O 操作是比较耗时的。
 * 我们在写代码的时候，应当尽量减少这类 I/O 操作。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class UserService {

    private UserRepo userRepo;//通过依赖注入或者IOC框架注入


    public User login(String email, String password) {

        // 隐蔽的执行重复:  实际上，login() 函数并不需要调用 checkIfUserExisted() 函数
        boolean existed = userRepo.checkIfUserExisted(email, password);
        if (!existed) {
            // ... throw AuthenticationFailureException...
        }

        User user = userRepo.getUserByEmail(email);

        // 校验密码正确性
        if (user == null || !password.equals(user.getPassword()) ) {
            // ... throw AuthenticationFailureException...
        }

        return user;

    }
}
