package design_patterns.chapter54.demo1.v2.unit;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>享元类的简单工厂</p>
 *
 * 享元：共享单元
 *
 *
 * 利用工厂类来缓存 ChessPieceUnit 信息（也就是 id、text、color）。通过工厂类获取到的 ChessPieceUnit 就是享元。
 * 所有的 ChessBoard 对象共享这 30 个 ChessPieceUnit 对象（因为象棋中只有 30 个棋子）。
 *
 * 在使用享元模式之前，
 *      记录 1 万个棋局，我们要创建 30 万（30*1 万）个棋子的 ChessPieceUnit 对象。
 *
 * 使用享元模式后，
 *      我们只需要创建 30 个 享元对象 供所有棋局共享使用即可，
 *
 * 总结一下享元模式的结构：
 *      它的代码实现非常简单，主要是通过 "工厂模式" ，
 *      在 工厂类 中，通过一个 Map 来缓存已经创建过的享元对象，来达到复用的目的。
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class ChessPieceUnitFactory {

    private static final Map<Integer, ChessPieceUnit> pieces = new HashMap<>();

    static {
        pieces.put(1, new ChessPieceUnit(1, "車", ChessPieceUnit.Color.BLACK));
        pieces.put(2, new ChessPieceUnit(2,"馬", ChessPieceUnit.Color.BLACK));

        //...省略摆放其他棋子的代码...

        pieces.put(1, new ChessPieceUnit(7, "車", ChessPieceUnit.Color.RED));
        pieces.put(2, new ChessPieceUnit(8,"馬", ChessPieceUnit.Color.RED));

        //...省略摆放其他棋子的代码...
    }

    public static ChessPieceUnit getChessPiece(int chessPieceId) {
        return pieces.get(chessPieceId);
    }
}
