package chapter29.demo1.v1.mock;


import chapter29.demo1.dependence.STATUS;
import chapter29.demo1.dependence.InvalidTransactionException;
import chapter29.demo1.v1.Transaction;
import org.junit.Assert;
import org.junit.Test;

/**
 * <p>测试用例</p>
 *
 * 1）正常情况下，
 *      交易执行成功，
 *      回填用于对账（交易与钱包的交易流水）用的 walletTransactionId，
 *      交易状态设置为 EXECUTED，
 *      函数返回 true。
 * 2）边界值
 *    buyerId、sellerId 为 null、amount 小于 0，返回 InvalidTransactionException。
 * 3）未决行为
 *    交易已过期（createTimestamp 超过 14 天），交易状态设置为 EXPIRED，返回 false。
 * 4）幂等性
 *    交易已经执行了（status==EXECUTED），不再重复执行转钱逻辑，返回 true。
 * 5）交易失败
 *    钱包（WalletRpcService）转钱失败，交易状态设置为 FAILED，函数返回 false。
 * 6）正在交易
 *    交易正在执行着，不会被重复执行，函数直接返回 false。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/20
 * </pre>
 */
public class TestDemo {

    @Test
    public void testExecute() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId);

        // 使用mock对象来替代真正的RPC服务
        transaction.setWalletRpcService(new MockWalletRpcServiceOne());
        boolean executedResult = transaction.execute();

        Assert.assertTrue(executedResult);
        Assert.assertEquals(STATUS.EXECUTED, transaction.getStatus());
    }
}
