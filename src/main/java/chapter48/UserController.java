package chapter48;

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
