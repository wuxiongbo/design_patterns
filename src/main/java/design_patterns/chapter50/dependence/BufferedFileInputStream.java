package design_patterns.chapter50.dependence;

import java.io.*;

/**
 * <p> Java IO 为什么不设计一个继承 FileInputStream 并且支持缓存的 BufferedFileInputStream 类呢？ </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/9/13
 * </pre>
 */
public class BufferedFileInputStream extends FileInputStream {

    public BufferedFileInputStream(String name) throws FileNotFoundException {
        super(name);
    }

    public BufferedFileInputStream(File file) throws FileNotFoundException {
        super(file);
    }

    public BufferedFileInputStream(FileDescriptor fdObj) {
        super(fdObj);
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
