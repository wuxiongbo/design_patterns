package design_patterns.chapter53.demo1.v2.node;

import lombok.Getter;

/**
 * <p>文件系统抽象类</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
@Getter
public abstract class AbstractFileSystemNode {

    /**
     * 文件/文件夹的路径
     */
    protected final String path;

    public AbstractFileSystemNode(String path) {
        this.path = path;
    }

    /**
     * 文件数
     * @return 文件数
     */
    public abstract int countNumOfFiles();

    /**
     * 文件大小
     * @return 文件大小
     */
    public abstract long countSizeOfFiles();

}
