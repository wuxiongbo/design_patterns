package demo16.bo;

import java.util.HashMap;
import java.util.Map;

// 告警规则
public class AlertRule {

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
    }
}
