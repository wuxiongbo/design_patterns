package chapter54.demo1.v2;

import chapter54.demo1.v2.unit.ChessPieceUnit;
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

    // 不变属性。   将棋子的 id、text、color 属性拆分出来，设计成独立的类，并且作为享元
    private ChessPieceUnit chessPieceUnit;

    // 变化属性。
    private int positionX;
    private int positionY;

    public ChessPiece(ChessPieceUnit unit, int positionX, int positionY) {
        this.chessPieceUnit = unit;
        this.positionX = positionX;
        this.positionY = positionY;
    }
    // 省略getter、setter方法
}
