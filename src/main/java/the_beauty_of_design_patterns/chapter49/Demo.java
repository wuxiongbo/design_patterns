package the_beauty_of_design_patterns.chapter49;

import the_beauty_of_design_patterns.chapter10.demo1.Main;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * <p>桥接模式</p>
 *
 * 桥接模式，也叫作 桥梁模式    Bridge Design Pattern
 *
 * // “抽象”
 * Abstraction：委托者的抽象                      没有抽象
 * RefinedAbstraction：委托者的具体实现            抽象：java.sql.DriverManager。依赖注入 接口 java.sql.Driver 的实现 com.mysql.jdbc.Driver
 *
 * // “实现”
 * Implementor：被委托者的接口                    接口：java.sql.Driver
 * ConcreteImplementor：被委托者的具体实现         实现：com.mysql.jdbc.Driver
 *
 *                                 委托
 *          抽象                                         实现
 *
 *       Abstraction   <>——————聚合——————    Implementor
 *           |                                       /         \
 *          泛化                                   泛化         泛化
 *           |                                    /              \
 *    RefinedAbstraction           ConcreteImplementorA      ConcreteImplementorB
 *
 *         委托者                                       被委托者
 *
 *
 *
 * 理解方式1 ：解耦
 *      将 “抽象” 和 “实现”  `解耦`，让它们可以独立变化。
 *          (注意：这里的 抽象、实现  并非指的 ‘类或接口’ 的 抽象、实现  ，而是 ‘业务侧’ 的 抽象、实现)
 *      这种理解方式比较特别，应用场景也不多。
 *
 * 理解方式2 ：扩展
 *      一个类 存在 两个 或 多个 独立变化的 '维度'，我们通过 “组合” 的方式，让这 两个或多个 '维度' 可以独立进行 `扩展`。
 *      通过 “组合关系” 来替代 “继承关系”，避免继承层次的指数级爆炸。非常类似于，“组合 优于 “继承” 设计原则。
 *      这种 理解方式 更加 通用，应用场景比较多。
 *      ( 对于 “维度”的理解，可以看示例 {@link Main})
 *
 *
 * 不管是哪种理解方式，它们的代码结构都是相同的，都是一种 类之间的“组合关系”。
 *
 *
 *
 * ================================================================================================================
 *
 *
 *
 * JDBC 驱动是 “桥接模式” 的经典应用。 此案例更偏向于 理解方式1
 * 下面开始解析：
 *
 *
 * 执行 Class.forName(“com.mysql.jdbc.Driver”) 这条语句的时候，实际上做了两件事情：
 *      第一件事情：要求 JVM ‘查找’ 并 ‘加载’ 指定的 Driver 类
 *      第二件事情：执行 Driver类 的静态代码，也就是将 MySQL Driver类 ‘注册’ 到 DriverManager 类 中
 *
 * @see Driver
 * Driver 类。  业务的“实现”
 * <pre>{@code
 *   public class Driver extends NonRegisteringDriver implements java.sql.Driver {
 *
 *       // Register ourselves with the DriverManager
 *       static {
 *           try {
 *               // 依赖注入 Driver。 为接下来的 "桥接"  作准备
 *               java.sql.DriverManager.registerDriver(new Driver());
 *           } catch (SQLException E) {
 *               throw new RuntimeException("Can't register driver!");
 *           }
 *       }
 *
 *       public Driver() throws SQLException {
 *           // Required for Class.forName().newInstance()
 *       }
 *   }
 * }</pre>
 *
 *
 *
 *
 *
 *
 * 当我们把具体的 Driver 实现类（比如，com.mysql.jdbc.Driver）注册到 DriverManager 之后，
 * 后续，所有对 JDBC 接口 的调用，都会 委托(委派)给 具体的 Driver 实现类来执行。
 * 而 Driver 实现类 都实现了相同的接口（java.sql.Driver），这就是 可以灵活切换 Driver 的原因。
 *
 *
 * @see DriverManager
 * DriverManager 类。  业务的 “抽象”
 * public class DriverManager {
 *
 *     // 利用 对象的 “组合”关系，将成员变量 作为  委托者 与 被委托者  交互的“桥梁”，起到了 ‘过桥’ 的作用。
 *     private final static CopyOnWriteArrayList<DriverInfo> registeredDrivers = new CopyOnWriteArrayList<>();
 *
 *
 *     // 依赖注入 com.mysql.jdbc.Driver
 *     public static synchronized void registerDriver(java.sql.Driver driver) throws SQLException {
 *         if (driver != null) {
 *             registeredDrivers.addIfAbsent(new DriverInfo(driver));
 *         } else {
 *             throw new NullPointerException();
 *         }
 *     }
 *
 *
 *     //  具体实现 过桥给  被委托者——com.mysql.jdbc.Driver
 *     private static Connection getConnection(String url, java.util.Properties info, Class<?> caller) throws SQLException {
 *
 *         //... 遍历 被委托者 列表
 *         for(DriverInfo aDriver : registeredDrivers) {
 *
 *             if(isDriverAllowed(aDriver.driver, callerCL)) {
 *                 try {
 *                     // 具体实现 委托 给了 Driver实现类，
 *                     // Driver实现类(aDriver.driver), 统一都是对 java.sql.Driver 接口 的实现
 *                     // 至此，达到了 “桥接” 目的
 *                     Connection con = aDriver.driver.connect(url, info);
 *                     if (con != null) {
 *                         return (con);
 *                     }
 *                 } catch (SQLException ex) {
 *                     if (reason == null) {
 *                         reason = ex;
 *                     }
 *                 }
 *             } else {
 *                 println("    skipping: " + aDriver.getClass().getName());
 *             }
 *
 *         }
 *         //...
 *     }
 *     //...
 * }
 *
 *
 * 更多 桥接模式的 应用示例：可参看 告警案例
 * @see the_beauty_of_design_patterns.chapter16.demo1.Main
 *
 *
 * sql驱动 还用到了 适配器模式
 * @see the_beauty_of_design_patterns.chapter51.demo5.Collections
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/21
 * </pre>
 */
public class Demo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");//加载及注册JDBC驱动程序
        String url = "jdbc:mysql://localhost:3306/sample_db?user=root&password=your_password";

        Connection con = DriverManager.getConnection(url);
        Statement stmt = con.createStatement();
        String query = "select * from test";


        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()) {
            rs.getString(1);
            rs.getInt(2);
        }
    }

}
