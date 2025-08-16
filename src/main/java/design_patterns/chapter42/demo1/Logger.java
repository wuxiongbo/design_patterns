package design_patterns.chapter42.demo1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p> 单例存在哪些问题 </p>
 *
 *
 * 单例对 OOP 特性的支持不友好
 *
 * 单例会隐藏类之间的依赖关系
 *
 * 单例对代码的扩展性不友好
 *
 * 单例对代码的可测试性不友好
 *
 * 单例不支持有参数的构造函数
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class Logger {
    private FileWriter writer;
    private static final Logger instance = new Logger();

    private Logger() {
        try {
            File file = new File("/Users/wangzheng/log.txt");
            writer = new FileWriter(file, true); //true表示追加写入
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        return instance;
    }

    public void log(String message) {
        try {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
