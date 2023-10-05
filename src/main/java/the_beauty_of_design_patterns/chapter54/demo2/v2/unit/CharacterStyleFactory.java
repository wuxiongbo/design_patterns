package the_beauty_of_design_patterns.chapter54.demo2.v2.unit;

import the_beauty_of_design_patterns.chapter54.depencence.Font;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class CharacterStyleFactory {

    private static final List<CharacterStyle> styles = new ArrayList<>();

    public static CharacterStyle getStyle(Font font, int size, int colorRGB) {
        // 懒加载
        CharacterStyle newStyle = new CharacterStyle(font, size, colorRGB);

        // 查找缓存
        for (CharacterStyle style : styles) {
            if (style.equals(newStyle)) {
                return style;
            }
        }

        // 加入缓存
        styles.add(newStyle);

        return newStyle;
    }
}
