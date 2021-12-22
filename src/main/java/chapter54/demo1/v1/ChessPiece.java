package chapter54.demo1.v1;

import lombok.Data;

/**
 * <p>棋子</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
@Data
public class ChessPiece {
    private int id;

    private String text;   // 棋子类型（将、相、士、炮等）
    private Color color;   // 棋子颜色（红方、黑方）

    private int positionX; // 棋子在棋局中的位置
    private int positionY; // 棋子在棋局中的位置

    public ChessPiece(int id, String text, Color color, int positionX, int positionY) {
        this.id = id;
        this.text = text;
        this.color = color;
        this.positionX = positionX;
        this.positionY = positionX;
    }

    public static enum Color {
        RED, BLACK
    }

    // ...省略其他属性和getter/setter方法...
}
