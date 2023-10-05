package the_beauty_of_design_patterns.chapter43.demo1.v2;

import the_beauty_of_design_patterns.chapter43.demo1.dependence.DistributedLock;
import the_beauty_of_design_patterns.chapter43.demo1.dependence.FileSharedObjectStorage;
import the_beauty_of_design_patterns.chapter43.demo1.dependence.SharedObjectStorage;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p> 集群唯一 </p>
 *  具体来说，我们需要把这个单例对象序列化并存储到外部共享存储区（比如文件）。
 *  进程在使用这个单例对象的时候，需要先从外部共享存储区中将它读取到内存，并反序列化成对象，然后再使用，使用完成之后还需要再存储回外部共享存储区。
 *  为了保证任何时刻，在进程间都只有一份对象存在，一个进程在获取到对象之后，需要对对象加锁，避免其他进程再将其获取。
 *  在进程使用完这个对象之后，还需要显式地将对象从内存中删除，并且释放对对象的加锁。
 *
 *  按照这个思路，用伪代码实现了一下这个过程
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/15
 * </pre>
 */

public class IdGenerator {
    private AtomicLong id = new AtomicLong(0);

    // 单例对象
    private static IdGenerator instance;

    // 对象序列化存储
    private static SharedObjectStorage storage = new FileSharedObjectStorage(/*入参省略，比如文件地址*/);

    // 分布式锁
    private static DistributedLock lock = new DistributedLock();

    private IdGenerator() {
    }

    // 获取实例
    public synchronized static IdGenerator getInstance() {
        if (instance == null) {
            lock.lock();
            instance = storage.load(IdGenerator.class);
        }
        return instance;
    }

    // 释放实例
    public synchronized void freeInstance() {
        storage.save(this, IdGenerator.class);
        instance = null; //释放对象
        lock.unlock();
    }

    public long getId() {
        return id.incrementAndGet();
    }


    public static void main(String[] args) {
        // IdGenerator使用举例

        // 获取实例
        IdGenerator idGenerator = IdGenerator.getInstance();
        long id = idGenerator.getId();

        // 使用完成后，释放实例
        idGenerator.freeInstance();

    }
}


