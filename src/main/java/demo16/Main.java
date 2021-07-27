package demo16;

import demo16.bo.ApiStatInfo;
import demo16.bo.ApplicationContext;

/**
 * 开闭原则：
 * 添加一个新的功能应该是，在已有代码基础上‘扩展’ 代码（新增模块、类、方法等），而非‘修改’ 已有代码（修改模块、类、方法等）
 */

public class Main {
    public static void main(String[] args) {

        ApiStatInfo apiStatInfo = new ApiStatInfo();

        // ...省略设置apiStatInfo其他数据值的代码
        // 改动四：设置timeoutCount值
        apiStatInfo.setTimeoutCount(289);


        ApplicationContext
                //单例模式 初始化对象
                .getInstance()
                .getAlert()
                .check(apiStatInfo);

    }
}
