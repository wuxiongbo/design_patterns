package chapter48.demo0.v1;

import chapter48.dependence.RequestInfo;
import chapter48.dependence.UserVo;

/**
 * <p>描述类的信息</p>
 *
 * 系统的 需求分析、设计和实现。  案例汇总。
 *
 * 业务系统的开发。 业务开发中的  设计原则和思想
 *    积分系统：
 *      涉及章节：
 *        23、24;
 *
 * 非业务系统(通用框架)的开发。
 *    计数系统：
 *      涉及章节：
 *        25、26;
 *        39、40;
 *
 * 以下代码有两个问题：
 *
 *   第一，性能计数器框架代码侵入到业务代码中，跟业务代码高度耦合。如果未来需要替换这个框架，那替换的成本会比较大。
 *
 *   第二，收集接口请求的代码跟业务代码无关，本就不应该放到一个类中。业务类最好职责更加单一，只聚焦业务处理。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/8/9
 * </pre>
 *
 */
public class UserController {

    //...省略其他属性和方法...
    private MetricsCollector metricsCollector; // 依赖注入





    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();



        // ... 省略login逻辑 ...



        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("login", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        //... 返回UserVo数据 ...
        return null;
    }


    public UserVo register(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();



        // ... 省略register逻辑...



        long endTimeStamp = System.currentTimeMillis();
        long responseTime = endTimeStamp - startTimestamp;
        RequestInfo requestInfo = new RequestInfo("register", responseTime, startTimestamp);
        metricsCollector.recordRequest(requestInfo);

        //...返回UserVo数据...
        return null;
    }


}
