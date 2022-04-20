package chapter29.demo1.v3.mock;

import chapter29.demo1.dependence.WalletRpcService;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/20
 * </pre>
 */
public class MockWalletRpcServiceTwo extends WalletRpcService {
    @Override
    public String moveMoney(String id, Long fromUserId, Long toUserId, Double amount) {
        return null;
    }
}
