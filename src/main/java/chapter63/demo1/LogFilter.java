package chapter63.demo1;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * <p> Handler 职责处理器</p>
 *
 * 过滤器：
 *     添加一个过滤器，我们只需要定义一个实现 javax.servlet.Filter 接口的过滤器类，并且将它配置在 web.xml 配置文件中 即可。
 *     Web 容器  在启动的时候，会加载并读取 web.xml 中的配置，创建过滤器对象。
 *     当有请求到来的时候，会先经过  一系列的 过滤器，然后才由 Servlet 来处理。
 *
 * 优点：
 *     我们发现，添加 过滤器 非常方便，不需要修改任何代码，
 *     定义一个实现 javax.servlet.Filter 的类，再改改 web.xml配置 就搞定了，完全符合 “开闭原则” 。
 *
 *
 * 问：Servlet Filter 是如何做到如此好的扩展性的呢？
 * 答：利用  “职责链模式”
 *
 *
 *
 * “职责链模式”  的 处理器接口（IHandler） 或  抽象类（Handler） 对应   Servlet Filter  的 处理器接口  javax.servlet.Filter
 * “职责链模式”  的 处理器链（HandlerChain）                   对应   Servlet Filter  的 处理器链    FilterChain
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/15
 * </pre>
 */
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 在创建Filter时自动调用，
        // 其中filterConfig包含这个Filter的配置参数，比如name之类的（从配置文件中读取的）
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("拦截客户端发送来的请求.");

        chain.doFilter(request, response);

        System.out.println("拦截发送给客户端的响应.");
    }


    @Override
    public void destroy() {
        // 在销毁Filter时自动调用
    }

}
