package chapter68.demo1.v2.resourcefile;

import chapter68.demo1.v2.Extractor;

/**
 * <p>描述类的信息</p>
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

    abstract public void accept(Extractor extractor);

}
