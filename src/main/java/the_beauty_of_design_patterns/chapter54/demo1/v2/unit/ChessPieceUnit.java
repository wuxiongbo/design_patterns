package the_beauty_of_design_patterns.chapter54.demo1.v2.unit;

import lombok.Data;

/**
 * <p>享元类</p>
 *
 * 享元：共享单元
 *
 *
 * 将棋子的 id、text、color 属性拆分出来，设计成独立的类，并且作为享元
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
@Data
public class ChessPieceUnit {
    private int id;
    private String text;
    private Color color;

    public ChessPieceUnit(int id, String text, Color color) {
        this.id = id;
        this.text = text;
        this.color = color;
    }

    public static enum Color {
        RED, BLACK
    }

    // ...省略其他属性和getter方法...
}
