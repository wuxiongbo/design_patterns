package chapter41.demo1.v1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/14
 * </pre>
 */
public class Logger {
    private FileWriter writer;

    public Logger()  {
        try {
            File file = new File("/Users/wangzheng/log.txt");
            writer = new FileWriter(file, true);   //true表示追加写入
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message){
        try {

            // 多线程写入，数据会被覆盖
            writer.write(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
