package demo16.bo;

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