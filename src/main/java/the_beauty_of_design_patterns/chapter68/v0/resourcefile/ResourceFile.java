package the_beauty_of_design_patterns.chapter68.v0.resourcefile;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/18
 * </pre>
 */

public abstract class ResourceFile {

    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    // 这部分业务逻辑，后续 可以与 数据解耦
    public abstract void extract2txt();
}
