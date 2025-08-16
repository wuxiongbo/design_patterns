package design_patterns.chapter41.demo1.v3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>单例模式</p>
 *
 * 实战案例一：处理资源访问冲突
 *
 * 使用单例创建 Logger 只存留一份 FileWriter 从而避免资源写入冲突问题
 *
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
