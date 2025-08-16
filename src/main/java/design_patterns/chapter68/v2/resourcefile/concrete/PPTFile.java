package design_patterns.chapter68.v2.resourcefile.concrete;

import design_patterns.chapter68.v2.function.Extractor;
import design_patterns.chapter68.v2.resourcefile.ResourceFile;

/**
 * <p>资源文件</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class PPTFile extends ResourceFile {
    public PPTFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Extractor extractor) {
        // 编译期，已经确认 this 是 PPTFile
        extractor.extract2txt(this);
    }
}
