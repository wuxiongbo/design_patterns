package the_beauty_of_design_patterns.chapter54.demo2.v2;

import the_beauty_of_design_patterns.chapter54.demo2.v2.unit.CharacterStyleFactory;
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
public class Editor {

    private List<Character> chars = new ArrayList<>();

    public void appendCharacter(char c, Font font, int size, int colorRGB) {
        Character character = new Character(c, CharacterStyleFactory.getStyle(font, size, colorRGB));
        chars.add(character);
    }
}
