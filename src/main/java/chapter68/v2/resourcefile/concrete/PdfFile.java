package chapter68.v2.resourcefile.concrete;

import chapter68.v2.function.Extractor;
import chapter68.v2.resourcefile.ResourceFile;

/**
 * <p>资源文件</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class PdfFile extends ResourceFile {
    public PdfFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Extractor extractor) {
        // 编译期，已经确认 this 是 PdfFile
        extractor.extract2txt(this);
    }
}
