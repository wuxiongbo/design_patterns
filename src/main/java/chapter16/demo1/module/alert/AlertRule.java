package chapter16.demo1.module.alert;

import java.util.HashMap;
import java.util.Map;

/**
 * 告警规则
 * */
public class AlertRule {

    // k: api，v: 对应的规则
    Map<String,MatchedRule> matchedRules = new HashMap<>();

    public MatchedRule getMatchedRule(String api) {
        return matchedRules.get(api);
    }


    // 规则匹配逻辑，写在内部类就行。
    // 因为，从业务逻辑上讲，告警规则 与 规则匹配 就属于一个模块
    public class MatchedRule {

        public long getMaxTps() {
            return 0;
        }

        public long getMaxErrorCount() {
            return 0;
        }

        public long getMaxTime() {
            return 0;
        }
    }


}
