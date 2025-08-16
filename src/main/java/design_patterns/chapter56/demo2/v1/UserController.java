package design_patterns.chapter56.demo2.v1;

import design_patterns.chapter56.dependence.PromotionService;
import design_patterns.chapter56.dependence.UserService;

/**
 * <p>观察者模式</p>
 *
 * 假设我们在开发一个 P2P 投资理财系统，用户注册成功之后，我们会给用户发放投资体验金。
 *
 * 代码实现大致是下面这个样子的：
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class UserController {
    private UserService userService; // 依赖注入
    private PromotionService promotionService; // 依赖注入

    public Long register(String telephone, String password) {
        //省略输入参数的校验代码
        //省略userService.register()异常的try-catch代码
        long userId = userService.register(telephone, password);

        promotionService.issueNewUserExperienceCash(userId);

        return userId;
    }
}
/**
*
* 虽然，注册接口做了两件事情，‘注册’ 和 ‘发放体验金’ ，违反 单一职责原则，
* 但是，如果没有 ‘扩展’ 和 ‘修改’ 的需求，现在的代码实现是可以接受的。
* 如果非得用观察者模式，就需要引入更多的类和更加复杂的代码结构，反倒是一种过度设计。
*
*
* 相反，如果需求频繁变动，
* 比如，
*      用户注册成功之后，不再 发放体验金，而是改为 发放优惠券，并且还要给用户发送一封“欢迎注册成功”的站内信。
*
* 这种情况下，我们就需要频繁地修改 register() 函数中的代码，违反开闭原则。
* 而且，如果注册成功之后需要执行的后续操作越来越多，那 register() 函数的逻辑会变得越来越复杂，也就影响到代码的可读性和可维护性。
*
*
* 这个时候，‘观察者模式’  就能派上用场了。
*
* */
