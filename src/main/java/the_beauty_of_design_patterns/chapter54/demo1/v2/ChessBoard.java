package the_beauty_of_design_patterns.chapter54.demo1.v2;

import the_beauty_of_design_patterns.chapter54.demo1.v2.unit.ChessPieceUnitFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>棋盘</p>
 *
 * 棋盘只需要记录每个棋子的位置信息
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class ChessBoard {
    // chessPieceId
    private Map<Integer, ChessPiece> chessPieces = new HashMap<>();

    public ChessBoard() {
        init();
    }

    private void init() {
        chessPieces.put(1, new ChessPiece(ChessPieceUnitFactory.getChessPiece(1), 0,0));
        chessPieces.put(2, new ChessPiece(ChessPieceUnitFactory.getChessPiece(2), 1,0));
        //...省略摆放其他棋子的代码...
    }

    public void move(int chessPieceId, int toPositionX, int toPositionY) {
        //...省略...
    }
}
