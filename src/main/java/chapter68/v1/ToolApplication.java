package chapter68.v1;

import chapter68.v1.resourcefile.ResourceFile;
import chapter68.v1.resourcefile.concrete.PPTFile;
import chapter68.v1.resourcefile.concrete.PdfFile;
import chapter68.v1.resourcefile.concrete.WordFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>访问者模式</p>
 *
 * 访问者模式 的演进
 *
 *
 * 持续开发：
 * 如果本工具的功能不停地扩展，
 *      不仅要能 抽取文本内容，
 *      还要  支持压缩、
 *      提取文件元信息（文件名、大小、更新时间等等）构建索引
 * 等一系列的功能，
 *
 * 我们继续按照本例的实现思路，就会存在这样几个问题：
 *   1）违背 ‘开闭原则’ ，添加一个新的功能，所有类的代码都要修改；
 *   2）虽然功能增多，每个类的代码都不断膨胀，可读性和可维护性都变差了；
 *   3）把所有比较上层的业务逻辑都耦合到 PdfFile、PPTFile、WordFile 类中，导致这些类的职责不够单一，变成了大杂烩。
 *
 *
 * 针对上面的问题，我们常用的解决方法就是  “拆分解耦” ，
 * 把 ‘业务操作’ 跟 ‘具体的数据结构’ 解耦，设计成独立的类。
 *
 * 这里我们按照 ‘访问者模式’ 的演进思路，来对上面的代码进行重构。
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class ToolApplication {

    public static void main(String[] args) {

        Extractor extractor = new Extractor();

        List<ResourceFile> resourceFiles = listAllResourceFiles(args[0]);

        // 尝试 通过 方法重载 实现扩展
        for (ResourceFile resourceFile : resourceFiles) {
            // 编译不通过。
//            extractor.extract2txt(resourceFile);
        }

    }

    // 资源文件列表
    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {

        List<ResourceFile> resourceFiles = new ArrayList<>();

        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)

        resourceFiles.add(new PdfFile(resourceDirectory+"a.pdf"));
        resourceFiles.add(new WordFile(resourceDirectory+"b.word"));
        resourceFiles.add(new PPTFile(resourceDirectory+"c.ppt"));

        return resourceFiles;

    }
}