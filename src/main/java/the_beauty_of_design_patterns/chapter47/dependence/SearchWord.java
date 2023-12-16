package the_beauty_of_design_patterns.chapter47.dependence;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/8/9
 * </pre>
 */
@Data
@AllArgsConstructor
public class SearchWord {
    // 关键字
    private String keyword;
    // 出现次数
    private int count;
    // 最后更新时间
    private long lastUpdateTime;
}
