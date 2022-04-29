package chapter53.demo1.v2.node;

/**
 * <p>文件系统抽象类</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/23
 * </pre>
 */
public abstract class FileSystemNode {

    protected String path;

    public FileSystemNode(String path) {
        this.path = path;
    }

    /**
     * 文件数
     * @return
     */
    public abstract int countNumOfFiles();

    /**
     * 文件大小
     * @return
     */
    public abstract long countSizeOfFiles();

    public String getPath() {
        return path;
    }
}
