package design_patterns.chapter68.v4;

import design_patterns.chapter68.v4.resourcefile.ResourceFile;
import design_patterns.chapter68.v4.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>访问者模式</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */
public class ToolApplication {

    // 1) 资源文件
    private final List<ResourceFile> resourceFiles = new ArrayList<>();

    public final void attach( ResourceFile resourceFile){
        resourceFiles.add(resourceFile);
    }

    public void operate(Visitor tool) {
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(tool);
        }
    }


}