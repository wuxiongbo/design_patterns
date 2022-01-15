package chapter63.demo2;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>描述类的信息</p>
 *
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
 *
 * Spring Interceptor 底层也是基于 职责链模式 实现的。
 * 其中，HandlerExecutionChain 类是职责链模式中的处理器链。
 * 它的实现相较于 Tomcat 中的 ApplicationFilterChain 来说，逻辑更加清晰，不需要使用递归来实现，
 *
 * 主要是因为它将‘请求’和‘响应’的拦截工作，
 * ‘拆分’ 到了两个函数中实现。
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