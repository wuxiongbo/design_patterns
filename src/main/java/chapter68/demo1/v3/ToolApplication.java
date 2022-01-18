package chapter68.demo1.v3;

import chapter68.demo1.v3.resourcefile.ResourceFile;
import chapter68.demo1.v3.resourcefile.concrete.PPTFile;
import chapter68.demo1.v3.resourcefile.concrete.PdfFile;
import chapter68.demo1.v3.resourcefile.concrete.WordFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>访问者模式</p>
 *
 * 代码还存在一些问题，添加一个新的业务，还是需要修改每个资源文件类{@link ResourceFile}，违反了开闭原则。
 *
 * 针对这个问题，我们抽象出来一个 Visitor 接口，包含是三个命名非常通用的 visit() 重载函数，分别处理三种不同类型的资源文件。
 * 具体做什么业务处理，由实现这个 Visitor 接口的具体的类来决定，比如 Extractor 负责抽取文本内容，Compressor 负责压缩。
 * 当我们新添加一个业务功能的时候，资源文件类{@link ResourceFile}不需要做任何修改，只需要修改 ToolApplication 的代码就可以了。
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


        Extractor extractor = new Extractor();
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(extractor);
        }



        Compressor compressor = new Compressor();
        for(ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(compressor);
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