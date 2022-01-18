package chapter68.demo1.v3.resourcefile.concrete;

import chapter68.demo1.v3.Compressor;
import chapter68.demo1.v3.Extractor;
import chapter68.demo1.v3.resourcefile.ResourceFile;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class WordFile extends ResourceFile {
    public WordFile(String filePath) {
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