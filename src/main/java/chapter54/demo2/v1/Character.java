package chapter54.demo2.v1;

import chapter54.depencence.Font;

/**
 * <p>文字</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class Character {

    // 文字
    private char c;

    // 格式。 格式包括文字的 字体、大小、颜色
    private Font font;
    private int size;
    private int colorRGB;

    public Character(char c, Font font, int size, int colorRGB) {
        this.c = c;
        this.font = font;
        this.size = size;
        this.colorRGB = colorRGB;
    }
}
