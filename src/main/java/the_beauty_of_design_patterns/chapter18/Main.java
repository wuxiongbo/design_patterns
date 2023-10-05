package the_beauty_of_design_patterns.chapter18;

import the_beauty_of_design_patterns.chapter18.config.KafkaConfig;
import the_beauty_of_design_patterns.chapter18.config.MysqlConfig;
import the_beauty_of_design_patterns.chapter18.config.RedisConfig;
import the_beauty_of_design_patterns.chapter18.metrics.ApiMetrics;
import the_beauty_of_design_patterns.chapter18.metrics.DbMetrics;
import the_beauty_of_design_patterns.chapter18.scheduled.ScheduledUpdater;
import the_beauty_of_design_patterns.chapter18.util.ConfigSource;
import the_beauty_of_design_patterns.chapter18.util.ZookeeperConfigSource;
import the_beauty_of_design_patterns.chapter18.view.SimpleHttpServer;

/**
 * <p>接口隔离原则</p>
 *  “客户端” 不应该 被强迫依赖 它不需要的接口。
 *  其中的“客户端”，可以理解为 接口的 “调用者” 或者 “使用者”。
 *
 *
 * 案例：假设我们的项目中用到了三个外部系统：Redis、MySQL、Kafka
 *
 *      每个系统，都对应一系列 配置信息，比如 地址、端口、访问超时时间 等。
 *      为了在内存中存储这些 配置信息，供项目中的其他模块来使用，我们分别设计实现了三个 Configuration 类：
 *          RedisConfig、MysqlConfig、KafkaConfig。
 *
 * 注意，本示例只给出了 RedisConfig 的代码实现，另外两个都是类似的，我这里就不贴了。
 *
 * 功能需求一：
 *      现在，我们有一个新的功能需求，希望支持 Redis 和 Kafka 配置信息的热更新。
 *
 *      所谓“热更新（hot update）”就是，如果在配置中心中更改了配置信息，我们希望在不用重启系统的情况下，
 *      能将最新的配置信息加载到内存中（也就是 RedisConfig、KafkaConfig 类中）。
 *      但是，因为某些原因，我们并不希望对 MySQL 的配置信息进行热更新。
 *
 * 功能需求二：
 *      刚刚的热更新的需求我们已经搞定了。现在，我们又有了一个新的监控功能需求。
 *
 *      通过命令行来查看 Zookeeper 中的配置信息是比较麻烦的。所以，我们希望能有一种更加方便的配置信息查看方式。
 *      我们可以在项目中开发一个内嵌的 SimpleHttpServer，输出项目的配置信息到一个固定的 HTTP 地址，比如：http://127.0.0.1:2389/config 。
 *      我们只需要在浏览器中输入这个地址，就可以显示出系统的配置信息。
 *      不过，出于某些原因，我们只想暴露 MySQL 和 Redis 的配置信息，不想暴露 Kafka 的配置信息。
 *
 *  Redis 热更新，暴露配置
 *  MySQL 不热更新，暴露配置
 *  Kafka 热更新，不暴露配置
 *
 * 不设计一个大而全的 Config 接口 ，而是设计 Updater 和 Viewer 两个小接口。
 * 这是为了满足 “接口隔离原则”
 *
 *
 * “接口隔离”的好处：
 *      假如，我们现在又有一个新的需求，开发一个 Metrics 性能统计模块，并且希望将 Metrics 也通过 SimpleHttpServer 显示在网页上，以方便查看。
 *      这个时候，尽管 Metrics 跟 RedisConfig 等没有任何关系，
 *      但我们仍然可以让 Metrics 类实现非常通用的 Viewer 接口，复用 SimpleHttpServer 的代码实现。
 *
 *  <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class Main {

    static ConfigSource configSource = new ZookeeperConfigSource(/*省略参数*/);

    public static final RedisConfig redisConfig = new RedisConfig(configSource);
    public static final KafkaConfig kafkaConfig = new KafkaConfig(configSource);
    public static final MysqlConfig mysqlConfig = new MysqlConfig(configSource);

    // 新需求
    public static final ApiMetrics apiMetrics = new ApiMetrics();
    public static final DbMetrics dbMetrics = new DbMetrics();

    public static void main(String[] args) {

        // 新增 ScheduledUpdater 定时任务类 和 Updater 接口。
        // 需要 定时更新的配置类  只需要去实现 该热更新接口 Updater 即可
        ScheduledUpdater redisConfigUpdater = new ScheduledUpdater(redisConfig, 300, 300);
        redisConfigUpdater.run();

        ScheduledUpdater kafkaConfigUpdater = new ScheduledUpdater(kafkaConfig, 60, 60);
        redisConfigUpdater.run();


        SimpleHttpServer simpleHttpServer = new SimpleHttpServer("127.0.0.1", 2389);
        simpleHttpServer.addViewer("/config", redisConfig);
        simpleHttpServer.addViewer("/config", mysqlConfig);
        simpleHttpServer.run();

    }

}
