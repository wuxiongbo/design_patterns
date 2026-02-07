package design_patterns.chapter29.demo1.v4;

import design_patterns.chapter29.demo1.dependence.IdGenerator;
import design_patterns.chapter29.demo1.dependence.InvalidTransactionException;
import design_patterns.chapter29.demo1.dependence.STATUS;
import design_patterns.chapter29.demo1.dependence.WalletRpcService;
import design_patterns.chapter29.demo1.v3.TransactionLock;
import lombok.Getter;
import lombok.Setter;


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
    private final Long buyerId;
    private final Long sellerId;
    private final Long productId;
    private final Long orderId;
    @Setter
    private Long createTimestamp;
    private Double amount;
    private STATUS status;
    private String walletTransactionId;
    private static final long DAYS = 24*60*60*1000L;


    //依赖注入是实现代码可测试性的最有效的手段。
    // 我们可以应用依赖注入，将 WalletRpcService 对象的创建反转给上层逻辑，在外部创建好之后，再注入到 Transaction 类中

    // 添加一个成员变量及其set方法
    @Setter
    private WalletRpcService walletRpcService;


    @Setter
    private TransactionLock lock;

    public void setTransactionLock(TransactionLock lock) {
        this.lock = lock;
    }



    // ...get() methods...


    protected boolean isExpired() {
        long executionInvokedTimestamp = System.currentTimeMillis();
        return executionInvokedTimestamp - createTimestamp > 14*DAYS;
    }

    // 抽取id生成逻辑
    //
    // 构造函数中并非只包含简单赋值操作。
    // 交易 id 的赋值逻辑稍微复杂。
    // 我们最好也要测试一下，以保证这部分逻辑的正确性。
    // 为了方便测试，我们可以把 id 赋值这部分逻辑单独抽象到一个函数中，具体的代码实现如下所示：
    protected void fillTransactionId(String preAssignedId) {
        if (preAssignedId != null && !preAssignedId.isEmpty()) {
            this.id = preAssignedId;
        } else {
            this.id = IdGenerator.generateTransactionId();
        }
        if (!this.id.startsWith("t_")) {
            this.id = "t_" + preAssignedId;
        }
    }

    public Transaction(String preAssignedId, Long buyerId, Long sellerId, Long productId, Long orderId) {
        fillTransactionId(preAssignedId);
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
//            isLocked = RedisDistributedLock.getSingletonInstance().lockTransction(id);
            isLocked = lock.lock(id);

            if (!isLocked) {
                return false; // 锁定未成功，返回false，job兜底执行
            }
            if (status == STATUS.EXECUTED) return true; // double check


            // 交易已过期（createTimestamp 超过 14 天），交易状态设置为 EXPIRED，返回 false。
            if (isExpired()) {
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
//                RedisDistributedLock.getSingletonInstance().unlockTransction(id);
                lock.unlock(id);
            }
        }
    }
}
