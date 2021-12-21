package chapter49;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * <p>桥接模式</p>
 * 桥接模式，也叫作 桥梁模式    Bridge Design Pattern
 *
 * 理解方式1 ：
 *      将 “抽象” 和 “实现” 解耦，让它们可以独立变化
 * 理解方式2 ：
 *      一个类存在两个（或多个）独立变化的维度，我们通过 “组合” 的方式，让这两个（或多个）维度可以独立进行扩展。
 *
 *      通过 “组合关系” 来替代 “继承关系”，避免继承层次的指数级爆炸。
 *      这种理解方式非常类似于，“组合 优于 继承” 设计原则
 *
 *
 * JDBC 驱动是 “桥接模式” 的经典应用。
 *
 * 下面开始解析：
 *
 *
 * 执行 Class.forName(“com.mysql.jdbc.Driver”) 这条语句的时候，实际上做了两件事情：
 *      第一件事情：要求 JVM ‘查找’ 并 ‘加载’ 指定的 Driver 类
 *      第二件事情：执行 Driver类 的静态代码，也就是将 MySQL Driver 注册到 DriverManager 类 中
 *
 *
 *
 * 业务的“实现”
 *
 * Driver 类。
 * public class Driver extends NonRegisteringDriver implements java.sql.Driver {
 *
 *     // Register ourselves with the DriverManager
 *     static {
 *         try {
 *             // 依赖注入 Driver。 为接下来的 "桥接"  作准备
 *             java.sql.DriverManager.registerDriver(new Driver());
 *         } catch (SQLException E) {
 *             throw new RuntimeException("Can't register driver!");
 *         }
 *     }
 *
 *     public Driver() throws SQLException {
 *         // Required for Class.forName().newInstance()
 *     }
 * }
 *
 *
 *
 *
 *
 * 当我们把具体的 Driver 实现类（比如，com.mysql.jdbc.Driver）注册到 DriverManager 之后，
 * 后续所有对 JDBC 接口的调用，都会 委派给 具体的 Driver 实现类来执行。
 * 而 Driver 实现类都实现了相同的接口（java.sql.Driver ），这也是可以灵活切换 Driver 的原因。
 *
 *
 * 业务的 “抽象”
 *
 * DriverManager 类
 * public class DriverManager {
 *
 *     // 利用 对象的 “组合”关系 ，作为 交互的“桥梁”
 *     // List of registered JDBC drivers
 *     private final static CopyOnWriteArrayList<DriverInfo> registeredDrivers = new CopyOnWriteArrayList<>();
 *
 *     //...
 *     static {
 *         loadInitialDrivers();
 *         println("JDBC DriverManager initialized");
 *     }
 *     //...
 *
 *     public static synchronized void registerDriver(java.sql.Driver driver) throws SQLException {
 *         if (driver != null) {
 *             registeredDrivers.addIfAbsent(new DriverInfo(driver));
 *         } else {
 *             throw new NullPointerException();
 *         }
 *     }
 *
 *
 *     private static Connection getConnection(String url, java.util.Properties info, Class<?> caller) throws SQLException {
 *         //...
 *         for(DriverInfo aDriver : registeredDrivers) {
 *
 *             if(isDriverAllowed(aDriver.driver, callerCL)) {
 *                 try {
 *                     // 委托 ，实现 “桥接”
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
 * }
 *
 *
 * @see chapter16.demo1.Main
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
