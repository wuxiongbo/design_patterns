package chapter68.demo1.v4.resourcefile;

import chapter68.demo1.v4.visitor.Visitor;
import chapter68.demo1.v4.visitor.concrete.Compressor;
import chapter68.demo1.v4.visitor.concrete.Extractor;

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

    abstract public void accept(Visitor vistor);
}
