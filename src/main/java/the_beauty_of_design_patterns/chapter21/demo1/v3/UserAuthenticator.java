package the_beauty_of_design_patterns.chapter21.demo1.v3;

import org.apache.commons.lang.StringUtils;

/**
 * <p> 实现逻辑重复。  解决方案二：重复代码 抽象成更细粒度函数  </p>
 *
 * 重新拆成合并前的那两个函数。
 *
 * 尽管代码的实现逻辑是相同的，但语义不同，我们判定它并不违反 DRY 原则。
 *
 * 最优解决方案：
 * 对于包含重复代码的问题，我们可以通过抽象成更细粒度函数的方式来解决。
 *
 * 比如, 将校验只包含 a~z、0~9、dot 的逻辑封装成   boolean onlyContains(String str, String charlist); 函数。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class UserAuthenticator {

    public void authenticate(String username, String password) {
        if (!isValidUsername(username)) {
            // ...throw InvalidUsernameException...
        }
        if (!isValidPassword(password)) {
            // ...throw InvalidPasswordException...
        }
        //...省略其他代码...
    }

    // 重新拆分成 并前的那两个函数
    private boolean isValidUsername(String username) {
        return onlyContains(username);
    }

    private boolean isValidPassword(String password) {
        return onlyContains(password);
    }

    // 重复逻辑抽象成 更细粒度的 函数
    private boolean onlyContains(String password) {
        // check not null, not empty
        if (StringUtils.isBlank(password)) {
            return false;
        }
        // check length: 4~64
        int length = password.length();
        if (length < 4 || length > 64) {
            return false;
        }
        // contains only lowcase characters
        if (!StringUtils.isAllLowerCase(password)) {
            return false;
        }
        // contains only a~z,0~9,dot
        for (int i = 0; i < length; ++i) {
            char c = password.charAt(i);
            if (!(c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.') {
                return false;
            }
        }
        return true;
    }

}
