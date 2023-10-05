package the_beauty_of_design_patterns.chapter59.callback_demo1;

import the_beauty_of_design_patterns.chapter59.dependence.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>同步回调</p>
 *
 * 原始代码：{@link JdbcDemo#queryUser(long)}
 * queryUser() 函数包含很多流程性质的代码，跟业务无关，
 *      比如，
 *          加载驱动、
 *          创建数据库连接、
 *          创建 statement、
 *          关闭连接、
 *          关闭 statement、
 *          处理异常。
 * 针对不同的 SQL 执行请求，这些流程性质的代码是相同的、可以复用的，我们不需要每次都重新敲一遍。
 *
 *
 *
 * 引入JdbcTemplate：
 *
 * 针对这个问题，Spring 提供了 JdbcTemplate，对 JDBC 进一步封装，来简化数据库编程。
 * 使用 JdbcTemplate 查询用户信息，我们只需要编写跟这个业务有关的代码（其中包括：查询 SQL 语句、查询结果 与 User 对象之间的映射关系）。
 * 其他流程性质的代码都封装在了 JdbcTemplate 类中，不需要我们每次都重新编写。
 *
 * 用 JdbcTemplate 重写上面的例子，代码简单很多，如下所示：
 *
 *
 * 那 JdbcTemplate 底层具体是如何实现的呢？
 * 我们来看一下它的关键源码。
 * {@link JdbcTemplate#query(java.lang.String, org.springframework.jdbc.core.ResultSetExtractor)}
 * {@link JdbcTemplate#execute(org.springframework.jdbc.core.StatementCallback, boolean)}
 *
 * 可以发现，JdbcTemplate 通过 “回调机制”  ，将不变的执行流程抽离出来，放到 模板方法 execute() 中，
 * 将可变的部分(SQL、映射关系) 设计成 回调(StatementCallback接口对象)，这部分由用户来定制。
 *
 * query() 函数，是对 execute() 函数的二次封装，让接口用起来更加方便。
 *
 *
 *
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/11
 * </pre>
 */
public class JdbcTemplateDemo {

    private JdbcTemplate jdbcTemplate;

    public User queryUser(long id) {

        // 可变因素一：SQL
        String sql = "select * from user where id="+id;


        return jdbcTemplate.query(
                    // SQL 语句
                    sql,
                    // '查询结果' 与 '对象' 之间的 映射关系
                    new UserRowMapper()
        ).get(0);

    }

    // 可变因素二： 表结构 与 对象实体 之间的 映射 关系
    //   "查询结果" 与 "User对象" 之间的映射关系
    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setTelephone(rs.getString("telephone"));
            return user;
        }
    }
}
