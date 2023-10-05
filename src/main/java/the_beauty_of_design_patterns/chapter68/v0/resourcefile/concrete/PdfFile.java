package the_beauty_of_design_patterns.chapter68.v0.resourcefile.concrete;

import the_beauty_of_design_patterns.chapter68.v0.resourcefile.ResourceFile;

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

    // 对数据的业务操作， 在资源文件中，这种设计 耦合性太强。
    @Override
    public void extract2txt() {

        // 从filePath 读取 源文件

        //...省略一大坨从PDF中抽取文本的代码...
        //...将抽取出来的文本保存在跟filePath同名的.txt文件中...

        System.out.println("Extract PDF.");
    }
}
