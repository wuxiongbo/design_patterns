package refactoring.chapter02;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * “花合理时间抓出⼤多数bug” 要好过“穷尽⼀⽣抓出所有bug”。
 * <p>
 * 测试代码和产品代码之间有个区别：你可以放⼼地复制、编辑测试代码。
 *
 * @author bear
 */
public class TestDemo {

    FileReader input;
    FileReader empty;

    @BeforeEach
    public void before() {
        try {
            input = new FileReader("data.txt");
            empty = newEmptyFile();
        } catch (IOException e) {
            throw new RuntimeException("unable to open test file");
        }
    }

    @Test
    public void testRead() throws IOException {
        char ch = '&';
        for (int i = 0; i < 4; i++) {
            ch = (char) input.read();
        }
        Assertions.assertEquals('d', ch);
    }

    /**
     * 区分失败和错误
     */
    @Test
    public void testReadAfterClose() throws IOException {
        char ch = '&';
        input.close();
        for (int i = 0; i < 4; i++) {
            ch = (char) input.read(); //will throw exception
        }
        Assertions.assertEquals('m', ch, "no exception for read past end");
    }

    /**
     * 编写未臻完善的测试并实际运⾏，好过对完美测试的⽆尽等待.
     * <p>
     * 现在，我的⽬光落到了read（）。它还应该做些什么？⽂档上说，当输⼊流到达 ⽂件尾端，read（）应该返回 -1
     */
    @Test
    public void testReadAtEnd() throws IOException {
        int ch = -1234;
        for (int i = 0; i < 141; i++) {
            ch = input.read();
        }
        Assertions.assertEquals(-1, input.read());
    }

    /**
     * 测试的⼀项重要技巧就是“寻找边界条件”。
     * 对read（）⽽⾔，边界条件应该是 第⼀个字符、最后⼀个字符、倒数第⼆个字符：
     *
     * @throws IOException
     */
    @Test
    public void testReadBoundaries() throws IOException {
        Assertions.assertEquals('B', input.read(), "read first char");
        int ch;
        for (int i = 1; i < 140; i++) {
            ch = input.read();
        }
        Assertions.assertEquals('6', -input.read(), "read last char");
        Assertions.assertEquals(-1, input.read(), "read at end");
        Assertions.assertEquals(-1, input.read(), "read past end");


    }

    /**
     * 考虑可能出错的边界条件，把测试⽕⼒集中在那⼉。
     * <p>
     * “寻找边界条件”也包括寻找特殊的、可能导致测试失败的情况。
     * 对于⽂件相关 测试，空⽂件是个不错的边界条件：
     * 测试空文件
     *
     * @throws IOException
     */
    @Test
    public void testEmptyRead() throws IOException {
        Assertions.assertEquals(-1, newEmptyFile().read());
    }

    private FileReader newEmptyFile() throws IOException {
        File empty = new File("empty.txt");
        FileOutputStream out = new FileOutputStream(empty);
        out.close();
        return new FileReader(empty);
    }


    @AfterEach
    public void after() {
        try {
            input.close();
            empty.close();
        } catch (IOException e) {
            throw new RuntimeException("error on closing test file");
        }
    }


}





