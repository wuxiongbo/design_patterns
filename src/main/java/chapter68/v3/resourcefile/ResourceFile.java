package chapter68.v3.resourcefile;

import chapter68.v3.function.Compressor;
import chapter68.v3.function.Extractor;

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


    abstract public void accept(Extractor extractor);
    // accept方法，会方法重载
    abstract public void accept(Compressor compressor);

}
