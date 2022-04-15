package chapter68.v4.resourcefile;

import chapter68.v4.visitor.Visitor;

/**
 * <p>资源文件</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */

public abstract class ResourceFile {

    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @param visitor
     */
    abstract public void accept(Visitor visitor);
}
