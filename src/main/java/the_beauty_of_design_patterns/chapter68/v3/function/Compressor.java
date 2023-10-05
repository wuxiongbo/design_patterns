package the_beauty_of_design_patterns.chapter68.v3.function;

import the_beauty_of_design_patterns.chapter68.v3.resourcefile.concrete.PPTFile;
import the_beauty_of_design_patterns.chapter68.v3.resourcefile.concrete.PdfFile;
import the_beauty_of_design_patterns.chapter68.v3.resourcefile.concrete.WordFile;

/**
 * <p>压缩器</p>
 *
 * 假设，我们现在 扩展一个新业务， 对文件进行压缩。
 * 只需要增加 Compressor 类，并对 资源文件类 稍作修改即可
 *
 * compress  压缩
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class Compressor {

    // compress 方法 方法重载
    public void compress(PPTFile pptFile) {
        //...
        System.out.println(pptFile.hashCode()+"compress PPT.");
    }

    public void compress(PdfFile pdfFile) {
        //...
        System.out.println(pdfFile.hashCode()+"compress PDF.");
    }

    public void compress(WordFile wordFile) {
        //...
        System.out.println(wordFile.hashCode()+"compress WORD.");
    }



}
