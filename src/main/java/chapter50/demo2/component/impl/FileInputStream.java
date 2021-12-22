package chapter50.demo2.component.impl;


import chapter50.demo2.component.InputStream;

import java.io.IOException;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/22
 * </pre>
 */
public class FileInputStream extends InputStream {

    public FileInputStream(String s) {
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
