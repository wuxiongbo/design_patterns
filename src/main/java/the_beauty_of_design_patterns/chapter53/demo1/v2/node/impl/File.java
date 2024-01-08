package the_beauty_of_design_patterns.chapter53.demo1.v2.node.impl;

import the_beauty_of_design_patterns.chapter53.demo1.v2.node.AbstractFileSystemNode;

/**
 * <p>文件</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
public class File extends AbstractFileSystemNode {

    public File(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        return 1;
    }

    @Override
    public long countSizeOfFiles() {
        java.io.File file = new java.io.File(path);
        if (!file.exists()) return 0;
        return file.length();
    }
}
