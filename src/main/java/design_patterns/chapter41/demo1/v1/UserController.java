package design_patterns.chapter41.demo1.v1;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class UserController {

    private Logger logger = new Logger();

    public void login(String username, String password)  {
        // ...省略业务逻辑代码...
        logger.log(username + " logined!");
    }

}
