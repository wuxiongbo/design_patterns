package chapter68.demo1.v3;

import chapter68.demo1.v3.resourcefile.concrete.PPTFile;
import chapter68.demo1.v3.resourcefile.concrete.PdfFile;
import chapter68.demo1.v3.resourcefile.concrete.WordFile;

/**
 * <p>描述类的信息</p>
 *
 *
 * compress  压缩
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class Compressor {

    // 方法重载
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
