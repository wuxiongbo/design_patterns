package the_beauty_of_design_patterns.chapter63.demo1;

import org.apache.catalina.core.ApplicationFilterConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

import static org.apache.catalina.core.ApplicationFilterChain.INCREMENT;

/**
 * <p> 责任链模式： 递归实现</p>
 *
 *
 *
 * 观察这部分关键代码：
 * @see org.apache.catalina.core.ApplicationFilterChain#internalDoFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
 *
 * ApplicationFilterChain 中的 doFilter() 函数的代码实现比较有技巧，实际上是一个递归调用。
 *
 *
 * 你可以用每个 Filter（比如 LogFilter）的 doFilter() 的代码实现，直接替换 ApplicationFilterChain 的第 12 行代码，一眼就能看出是递归调用了。
 * 替换后，如下所示。
 *
 * 数据结构 图
 * https://www.edrawmax.cn/online/share.html?code=014e2d0c75cd11ec81ccd9ad3e6d60f3
 *
 *
 * 这样实现主要目的是：
 *     为了在一个 doFilter() 方法中，支持 “双向拦截” ，
 *     既能 拦截 ‘客户端’   发送给‘web容器’ 的  请求，
 *     也能 拦截 ‘web容器’ 发送给‘客户端’   的  响应，
 *
 * 可以结合着 {@link LogFilter} 的例子，理解一下。
 *
 * 而我们上一节课给出的两种实现方式，都没法做到 在 业务逻辑 执行的 前、后，同时添加 处理代码。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/15
 * </pre>
 */
public class ApplicationFilterChain implements FilterChain {

    private int pos = 0;  //当前执行到了哪个filter
    private int n;        //filter的个数

    //基于 数组 实现的责任链
    private ApplicationFilterConfig[] filters;

    private Servlet servlet;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        if (pos < n) {

            ApplicationFilterConfig filterConfig = filters[pos++];

//            Filter filter = filterConfig.getFilter();


//            filter.doFilter(request, response, this);

            //  把filter.doFilter的代码实现，展开 替换到这里
            System.out.println("拦截客户端发送来的请求.");
            this.doFilter(request, response); // 这里的 this ，就是 LogFilter类 doFilter方法的 chain变量
            System.out.println("拦截发送给客户端的响应.");

        } else {
            // filter都处理完毕后，执行servlet
            servlet.service(request, response);
        }

    }


    public void addFilter(ApplicationFilterConfig filterConfig) {

        // 防止重复添加
        for (ApplicationFilterConfig filter:filters)
            if (filter==filterConfig)
                return;

        // 扩容
        if (n == filters.length) {
            ApplicationFilterConfig[] newFilters = new ApplicationFilterConfig[n + INCREMENT];
            System.arraycopy(filters, 0, newFilters, 0, n);
            filters = newFilters;
        }

        // 添加新过滤器。 计数+1
        filters[n++] = filterConfig;

    }

}