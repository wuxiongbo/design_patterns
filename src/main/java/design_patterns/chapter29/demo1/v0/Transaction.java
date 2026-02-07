package design_patterns.chapter29.demo1.v0;

import design_patterns.chapter29.demo1.dependence.IdGenerator;
import design_patterns.chapter29.demo1.dependence.RedisDistributedLock;
import design_patterns.chapter29.demo1.dependence.WalletRpcService;
import design_patterns.chapter29.demo1.dependence.InvalidTransactionException;
import design_patterns.chapter29.demo1.dependence.STATUS;
import lombok.Data;


/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/4/20
 * </pre>
 */
@Data
public class Transaction {
    private String id;
    private final Long buyerId;
    private final Long sellerId;
    private final Long productId;
    private final Long orderId;
    private final Long createTimestamp;
    private Double amount;
    private STATUS status;
    private String walletTransactionId;
    private static final long DAYS = 24*60*60*1000L;


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
            WalletRpcService walletRpcService = new WalletRpcService();

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
