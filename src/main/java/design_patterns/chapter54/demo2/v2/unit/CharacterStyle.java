package design_patterns.chapter54.demo2.v2.unit;

import design_patterns.chapter54.depencence.Font;

/**
 * <p>文本格式</p>
 *
 * 字体、大小、颜色等
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class CharacterStyle {

    private Font font;
    private int size;
    private int colorRGB;

    public CharacterStyle(Font font, int size, int colorRGB) {
        this.font = font;
        this.size = size;
        this.colorRGB = colorRGB;
    }

    @Override
    public boolean equals(Object o) {

        CharacterStyle otherStyle = (CharacterStyle) o;

        // 字体、大小、颜色等 相同 ，则视为统一格式
        return font.equals(otherStyle.font)
                && size == otherStyle.size
                && colorRGB == otherStyle.colorRGB;
    }
}
