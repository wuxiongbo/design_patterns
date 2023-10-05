package the_beauty_of_design_patterns.chapter48.demo0.v2.controller;

import the_beauty_of_design_patterns.chapter48.dependence.UserVo;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class UserController implements IUserController {
    //...省略其他属性和方法...

    @Override
    public UserVo login(String telephone, String password) {


        //...省略login逻辑...


        //...返回UserVo数据...
        return null;
    }

    @Override
    public UserVo register(String telephone, String password) {

        //...省略register逻辑...

        //...返回UserVo数据...
        return null;
    }
}
