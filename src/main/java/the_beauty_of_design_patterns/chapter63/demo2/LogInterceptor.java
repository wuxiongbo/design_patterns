package the_beauty_of_design_patterns.chapter63.demo2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>职责链模式</p>
 *
 *
 * 一、Interceptor 与 Filter区别：
 *
 * LogInterceptor 实现的功能跟刚才的 LogFilter 完全相同，只是实现方式上稍有区别。
 *
 * LogFilter 对请求和响应的拦截都是在 doFilter() 一个函数中实现的，
 *
 * 而 LogInterceptor
 *      对‘请求’的拦截 在 preHandle() 中实现，
 *      对‘响应’的拦截 在 postHandle() 中实现。
 *
 *
 * Spring Interceptor 底层也是基于 职责链模式 实现的。
 * 其中，HandlerExecutionChain 类是职责链模式中的处理器链。
 * 它的实现相较于 Tomcat 中的 ApplicationFilterChain 来说，逻辑更加清晰，不需要使用递归来实现，
 *
 * 主要是因为它将‘请求’和‘响应’的拦截工作，
 * ‘拆分’ 到了两个函数中实现。
 *
 *
 * 我们通过 Servlet Filter、Spring Interceptor 两个实际的例子，展示了在框架开发中职责链模式具体是怎么应用的。
 * 从源码中，我们还可以发现，尽管上一节课中我们有给出 职责链模式 的  经典代码实现，
 *
 * 但在实际的开发中，我们还是要“具体问题，具体对待”，
 * 代码实现 会根据不同的需求有所变化。实际上，这一点对于所有的设计模式都适用。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/15
 * </pre>
 */
public class LogInterceptor implements HandlerInterceptor {


    // 拦截 请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截客户端发送来的请求.");
        return true; // 继续后续的处理
    }


    // 拦截 响应
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截发送给客户端的响应.");
    }



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("这里总是被执行.");
    }

}