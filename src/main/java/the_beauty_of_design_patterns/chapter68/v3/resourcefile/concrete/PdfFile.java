package the_beauty_of_design_patterns.chapter68.v3.resourcefile.concrete;

import the_beauty_of_design_patterns.chapter68.v3.function.Compressor;
import the_beauty_of_design_patterns.chapter68.v3.function.Extractor;
import the_beauty_of_design_patterns.chapter68.v3.resourcefile.ResourceFile;

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
        extractor.extract2txt(this);
    }

    @Override
    public void accept(Compressor compressor) {
        compressor.compress(this);
    }

}
