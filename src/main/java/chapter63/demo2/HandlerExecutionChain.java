package chapter63.demo2;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/15
 * </pre>
 */
public class HandlerExecutionChain {

    private int interceptorIndex = -1;

    private final Object handler = null;
    private final List<HandlerInterceptor> interceptorList = new ArrayList<>();


    public void addInterceptor(HandlerInterceptor interceptor) {
        this.interceptorList.add(interceptor);
    }



    boolean applyPreHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {

        for (int i = 0; i < this.interceptorList.size(); i++) {

            HandlerInterceptor interceptor = this.interceptorList.get(i);

            if (!interceptor.preHandle(request, response, this.handler)) {
                triggerAfterCompletion(request, response, null);
                return false;
            }

            this.interceptorIndex = i;
        }

        return true;
    }



    void applyPostHandle(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) throws Exception {

        for (int i = this.interceptorList.size() - 1; i >= 0; i--) {
            HandlerInterceptor interceptor = this.interceptorList.get(i);
            interceptor.postHandle(request, response, this.handler, mv);
        }

    }


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
