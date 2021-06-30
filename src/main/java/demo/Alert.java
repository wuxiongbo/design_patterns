package demo;

import demo.handler.AlertHandler;

import java.util.ArrayList;
import java.util.List;

// 告警
public class Alert {
    private List<AlertHandler> alertHandlers = new ArrayList<>();

    public void addAlertHandler(AlertHandler alertHandler) {
        this.alertHandlers.add(alertHandler);
    }

    // Alert扩展性改造一:  将函数的多个入参封装成 ApiStatInfo 类；
    public void check(ApiStatInfo apiStatInfo) {
        // Alert扩展性改造二:  引入 handler 的概念，将 if 判断逻辑分散在各个 handler 中。
        for (AlertHandler handler : alertHandlers) {
            handler.check(apiStatInfo);
        }
    }

    // 重构之后的代码更加灵活和易扩展。
    // 想添加新的告警逻辑，只需要基于扩展的方式创建新的 handler 类即可，不需要改动原来的 check() 函数的逻辑。
    // 而且，我们只需要为新的 handler 类添加单元测试，老的单元测试都不会失败，也不用修改。

}
