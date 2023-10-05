package the_beauty_of_design_patterns.chapter50.demo2.component.impl;


import the_beauty_of_design_patterns.chapter50.demo2.component.InputStream;

import java.io.IOException;

/**
 * <p> 实现类： 文件输入流 </p>
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
