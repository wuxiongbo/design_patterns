package the_beauty_of_design_patterns.chapter68.v2.resourcefile;

import the_beauty_of_design_patterns.chapter68.v2.function.Extractor;

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

}
