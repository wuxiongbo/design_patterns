package the_beauty_of_design_patterns.chapter29.demo1.v1.mock;

import the_beauty_of_design_patterns.chapter29.demo1.dependence.WalletRpcService;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/20
 * </pre>
 */

public class MockWalletRpcServiceOne extends WalletRpcService {
    @Override
    public String moveMoney(String id, Long fromUserId, Long toUserId, Double amount) {
        return "123bac";
    }
}

