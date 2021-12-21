package chapter48.demo0.v2;

import chapter48.dependence.UserVo;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public interface IUserController {

    UserVo login(String telephone, String password);

    UserVo register(String telephone, String password);

}
