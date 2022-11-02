package chapter63.demo2;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> HandlerExecutionChain 源码 简版</p>
 *
 * {@link org.springframework.web.servlet.HandlerExecutionChain}
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/15
 * </pre>
 */
public class HandlerExecutionChain {

    private int interceptorIndex = -1;

    private final Object handler = null;

    // 基于数组实现的 责任链
    private final List<HandlerInterceptor> interceptorList = new ArrayList<>();


    // 1）添加 处理器
    public void addInterceptor(HandlerInterceptor interceptor) {
        this.interceptorList.add(interceptor);
    }


    // 2）触发 请求拦截
    boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 顺向遍历，判断是否传递
        for (int i = 0; i < this.interceptorList.size(); i++) {

            HandlerInterceptor interceptor = this.interceptorList.get(i);

            if (!interceptor.preHandle(request, response, this.handler)) { // 动作传递

                // 被拦截。则触发 完成请求动作
                triggerAfterCompletion(request, response, null);
                return false;
            }

            this.interceptorIndex = i;
        }

        return true;
    }


    // 2）触发 响应拦截
    void applyPostHandle(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) throws Exception {

        // 反向遍历，一直传递
        for (int i = this.interceptorList.size() - 1; i >= 0; i--) {

            HandlerInterceptor interceptor = this.interceptorList.get(i);

            interceptor.postHandle(request, response, this.handler, mv);    // 动作传递

        }

    }

    // 2）在完成请求并生成视图后调用
    void triggerAfterCompletion(HttpServletRequest request, HttpServletResponse response, Exception ex) {

        for (int i = this.interceptorIndex; i >= 0; i--) {

            HandlerInterceptor interceptor = this.interceptorList.get(i);

            try {
                interceptor.afterCompletion(request, response, this.handler, ex);
            }
            catch (Throwable ex2) {
            }

        }

    }

}
