package chapter68.demo1.v0.resourcefile.concrete;

import chapter68.demo1.v0.resourcefile.ResourceFile;

/**
 * <p>描述类的信息</p>
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
    public void extract2txt() {

        // 从filePath 读取 源文件

        //...省略一大坨从PPT中抽取文本的代码...
        //...将抽取出来的文本保存在跟filePath同名的.txt文件中...

        System.out.println("Extract PDF.");
    }
}
