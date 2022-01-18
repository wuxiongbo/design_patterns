package chapter68.demo1.v0;

import chapter68.demo1.v0.resourcefile.concrete.PPTFile;
import chapter68.demo1.v0.resourcefile.concrete.PdfFile;
import chapter68.demo1.v0.resourcefile.ResourceFile;
import chapter68.demo1.v0.resourcefile.concrete.WordFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>访问者模式</p>
 *
 * 如果工具的功能不停地扩展，不仅要能抽取文本内容，还要支持压缩、提取文件元信息（文件名、大小、更新时间等等）构建索引等一系列的功能，
 * 那如果我们继续按照上面的实现思路，就会存在这样几个问题：
 *   1）违背开闭原则，添加一个新的功能，所有类的代码都要修改；
 *   2）虽然功能增多，每个类的代码都不断膨胀，可读性和可维护性都变差了；
 *   3）把所有比较上层的业务逻辑都耦合到 PdfFile、PPTFile、WordFile 类中，导致这些类的职责不够单一，变成了大杂烩。
 *
 *
 * 针对上面的问题，我们常用的解决方法就是  “拆分解耦” ，把业务操作跟具体的数据结构解耦，设计成独立的类。
 * 这里我们按照 访问者模式 的演进思路来对上面的代码进行重构。
 *
 *
 *
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class ToolApplication {

    public static void main(String[] args) {

        List<ResourceFile> resourceFiles = listAllResourceFiles(args[0]);

        // 在 ToolApplication 中，利用 多态特性，根据对象的实际类型，来决定执行哪个方法。
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.extract2txt();
        }

    }


    private static List<ResourceFile> listAllResourceFiles(String resourceDirectory) {

        List<ResourceFile> resourceFiles = new ArrayList<>();

        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)

        resourceFiles.add(new PdfFile(resourceDirectory+"a.pdf"));
        resourceFiles.add(new WordFile(resourceDirectory+"b.word"));
        resourceFiles.add(new PPTFile(resourceDirectory+"c.ppt"));

        return resourceFiles;

    }
}