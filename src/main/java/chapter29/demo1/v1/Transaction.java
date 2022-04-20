package chapter29.demo1.v1;

import chapter29.demo1.dependence.IdGenerator;
import chapter29.demo1.dependence.RedisDistributedLock;
import chapter29.demo1.dependence.STATUS;
import chapter29.demo1.dependence.WalletRpcService;
import chapter29.demo1.dependence.InvalidTransactionException;
import lombok.Getter;


/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/20
 * </pre>
 */
@Getter
public class Transaction {
    private String id;
    private Long buyerId;
    private Long sellerId;
    private Long productId;
    private Long orderId;
    private Long createTimestamp;
    private Double amount;
    private STATUS status;
    private String walletTransactionId;
    private static final long DAYS = 24*60*60*1000L;


    //依赖注入是实现代码可测试性的最有效的手段。
    // 我们可以应用依赖注入，将 WalletRpcService 对象的创建反转给上层逻辑，在外部创建好之后，再注入到 Transaction 类中

    // 添加一个成员变量及其set方法
    private WalletRpcService walletRpcService;
    public void setWalletRpcService(WalletRpcService walletRpcService) {
        this.walletRpcService = walletRpcService;
    }


    // ...get() methods...

    public Transaction(String preAssignedId, Long buyerId, Long sellerId, Long productId, Long orderId) {
        if (preAssignedId != null && !preAssignedId.isEmpty()) {
            this.id = preAssignedId;
        } else {
            this.id = IdGenerator.generateTransactionId();
        }
        if (!this.id.startsWith("t_")) {
            this.id = "t_" + preAssignedId;
        }
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.orderId = orderId;
        this.status = STATUS.TO_BE_EXECUTED;
        this.createTimestamp = System.currentTimeMillis();
    }

    public boolean execute() throws InvalidTransactionException {
        if ((buyerId == null || (sellerId == null || amount < 0.0))){
            throw new InvalidTransactionException(/*...*/);
        }
        // EXECUTED 交易已生效
        if (status == STATUS.EXECUTED) return true;

        boolean isLocked = false;
        try {
            // 依赖的外部服务
            isLocked = RedisDistributedLock.getSingletonInstance().lockTransction(id);
            if (!isLocked) {
                return false; // 锁定未成功，返回false，job兜底执行
            }
            if (status == STATUS.EXECUTED) return true; // double check


            // 交易已过期（createTimestamp 超过 14 天），交易状态设置为 EXPIRED，返回 false。
            long executionInvokedTimestamp = System.currentTimeMillis();
            if (executionInvokedTimestamp - createTimestamp > 14*DAYS) {
                this.status = STATUS.EXPIRED;
                return false;
            }

            // 依赖的外部服务
//            WalletRpcService walletRpcService = new WalletRpcService();

            // 返回用于对账（交易与钱包的交易流水）用的 walletTransactionId，
            String walletTransactionId = walletRpcService.moveMoney(id, buyerId, sellerId, amount);

            // 交易执行成功，交易状态设置为 EXECUTED，函数返回 true。
            if (walletTransactionId != null) {
                this.walletTransactionId = walletTransactionId;
                this.status = STATUS.EXECUTED;
                return true;
            }
            // 交易执行失败
            else {
                this.status = STATUS.FAILED;
                return false;
            }


        } finally {
            if (isLocked) {
                RedisDistributedLock.getSingletonInstance().unlockTransction(id);
            }
        }
    }
}
