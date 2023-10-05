package the_beauty_of_design_patterns.chapter46.v2;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/3/11
 * </pre>
 */
public class Main {

    public static void main(String[] args){
        // 这段代码会抛出IllegalArgumentException，因为  minIdle > maxIdle
        ResourcePoolConfig config = new ResourcePoolConfig.Builder()
                .setName("dbconnectionpool")
                .setMaxTotal(16)
                .setMaxIdle(10)
                .setMinIdle(12)
                // 触发校验
                .build();
    }

}
