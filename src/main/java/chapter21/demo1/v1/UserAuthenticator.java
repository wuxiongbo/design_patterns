package chapter21.demo1.v1;

import org.apache.commons.lang.StringUtils;

/**
 * <p> 实现逻辑重复 </p>
 *
 *
 * DRY 原则  示例
 * “不要写重复的代码”
 *
 * 在代码中，有两处非常明显的重复的代码片段：isValidUserName() 函数和 isValidPassword() 函数。
 *
 * 重复的代码被敲了两遍，或者简单 copy-paste 了一下，看起来明显违反 DRY 原则。 实际上并不违反，具体后面解释
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

    private boolean isValidUsername(String username) {
        // check not null, not empty
        if (StringUtils.isBlank(username)) {
            return false;
        }
        // check length: 4~64
        int length = username.length();
        if (length < 4 || length > 64) {
            return false;
        }
        // contains only lowcase characters
        if (!StringUtils.isAllLowerCase(username)) {
            return false;
        }
        // contains only a~z,0~9,dot
        for (int i = 0; i < length; ++i) {
            char c = username.charAt(i);
            if (!(c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.') {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPassword(String password) {
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
