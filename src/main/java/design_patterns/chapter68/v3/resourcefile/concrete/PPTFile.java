package design_patterns.chapter68.v3.resourcefile.concrete;

import design_patterns.chapter68.v3.function.Compressor;
import design_patterns.chapter68.v3.function.Extractor;
import design_patterns.chapter68.v3.resourcefile.ResourceFile;

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
        extractor.extract2txt(this);
    }

    @Override
    public void accept(Compressor compressor) {
        compressor.compress(this);
    }

}
