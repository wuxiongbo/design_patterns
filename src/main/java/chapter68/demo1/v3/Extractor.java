package chapter68.demo1.v3;

import chapter68.demo1.v3.resourcefile.concrete.PPTFile;
import chapter68.demo1.v3.resourcefile.concrete.PdfFile;
import chapter68.demo1.v3.resourcefile.concrete.WordFile;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class Extractor {

    // 方法重载
    public void extract2txt(PPTFile pptFile) {
        //...
        System.out.println(pptFile.hashCode()+"Extract PPT.");
    }

    public void extract2txt(PdfFile pdfFile) {
        //...
        System.out.println(pdfFile.hashCode()+"Extract PDF.");
    }

    public void extract2txt(WordFile wordFile) {
        //...
        System.out.println(wordFile.hashCode()+"Extract WORD.");
    }

}
