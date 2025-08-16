package design_patterns.chapter21.demo1.v2;

import org.apache.commons.lang.StringUtils;

/**
 * <p> 实现逻辑重复。  解决方案一：合并函数，移除重复的代码 </p>
 *
 * 移除重复的代码：
 * 我们对上一版的代码做下重构，将 isValidUserName() 函数和 isValidPassword() 函数，
 * 合并为一个更通用的函数 isValidUserNameOrPassword()。
 *
 * 经过重构之后，代码行数减少了，也没有重复的代码了，是不是更好了呢？
 * 答案是否定的，这可能跟你预期的不一样，我来解释一下为什么。
 *
 * 违背原则：
 * 单从名字上看，我们就能发现，合并之后的 isValidUserNameOrPassword() 函数，负责两件事情：验证用户名和验证密码。
 * 违反了“单一职责原则”和“接口隔离原则”。
 * 实际上，即便将两个函数合并成 isValidUserNameOrPassword()，代码仍然存在问题。
 *
 *
 *
 * 语义不重复：
 * 因为 isValidUserName() 和 isValidPassword() 两个函数，虽然从 代码实现逻辑 上看起来是重复的，但是从 语义 上并不重复。
 * 所谓“语义不重复”指的是：从功能上来看，这两个函数干的是完全不重复的两件事情，一个是校验 ‘用户名’，另一个是校验 ‘密码’。
 *
 * 存在问题：
 * 尽管在目前的设计中，两个校验逻辑是完全一样的，但如果按照第二种写法，将两个函数的合并，那就会存在潜在的问题。
 * 在未来的某一天，如果我们修改了密码的校验逻辑，
 * 比如，允许密码包含大写字符，允许密码的长度为 8 到 64 个字符，那这个时候，isValidUserName() 和 isValidPassword() 的实现逻辑就会不相同。
 * 我们就要把合并后的函数，重新拆成合并前的那两个函数。
 *
 * 尽管代码的实现逻辑是相同的，但语义不同，我们判定它并不违反 DRY 原则。
 *
 * 最优解决方案：
 * 对于包含重复代码的问题，我们可以通过抽象成更细粒度函数的方式来解决。
 * 比如, 将校验只包含 a~z、0~9、dot 的逻辑封装成 boolean onlyContains(String str, String charlist); 函数。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class UserAuthenticator {

    public void authenticate(String username, String password) {
        if (!isValidUsernameOrPassword(username)) {
            // ...throw InvalidUsernameException...
        }

        if (!isValidUsernameOrPassword(password)) {
            // ...throw InvalidPasswordException...
        }
        //...省略其他代码...
    }

    private boolean isValidUsernameOrPassword(String usernameOrPassword) {
        // check not null, not empty
        if (StringUtils.isBlank(usernameOrPassword)) {
            return false;
        }
        // check length: 4~64
        int length = usernameOrPassword.length();
        if (length < 4 || length > 64) {
            return false;
        }
        // contains only lowcase characters
        if (!StringUtils.isAllLowerCase(usernameOrPassword)) {
            return false;
        }
        // contains only a~z,0~9,dot
        for (int i = 0; i < length; ++i) {
            char c = usernameOrPassword.charAt(i);
            if (!(c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '.') {
                return false;
            }
        }
        return true;
    }

}
