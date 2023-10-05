package the_beauty_of_design_patterns.chapter54.demo2.v2;

import the_beauty_of_design_patterns.chapter54.demo2.v2.unit.CharacterStyle;

/**
 * <p>文字</p>
 *
 * 设计的核心思路：
 * 实际上，在一个文本文件中，用到的字体格式不会太多，毕竟不大可能有人把每个文字都设置成不同的格式。
 * 所以，对于字体格式，我们可以将它设计成享元，让不同的文字共享使用。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public class Character {

    private char c;

    // 抽取不变属性，设置成享元
    private CharacterStyle style;

    public Character(char c, CharacterStyle style) {
        this.c = c;
        this.style = style;
    }
}
