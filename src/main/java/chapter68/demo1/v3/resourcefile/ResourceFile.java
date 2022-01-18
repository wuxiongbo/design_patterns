package chapter68.demo1.v3.resourcefile;

import chapter68.demo1.v3.Compressor;
import chapter68.demo1.v3.Extractor;

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


    // 方法重载
    abstract public void accept(Extractor extractor);

    abstract public void accept(Compressor compressor);

}
