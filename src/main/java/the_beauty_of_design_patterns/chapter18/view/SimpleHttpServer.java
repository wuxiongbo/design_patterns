package the_beauty_of_design_patterns.chapter18.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>描述类的信息</p>
 *
 * SimpleHttpServer 只依赖跟查看信息相关的 Viewer 接口，不依赖不需要的 Updater 接口，
 * 也满足"接口隔离原则"
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/7/28
 * </pre>
 */
public class SimpleHttpServer {
    private String host;
    private int port;
    private Map<String, List<Viewer>> viewers = new HashMap<>();

    public SimpleHttpServer(String host, int port) {
        //...
    }

    public void addViewer(String urlDirectory, Viewer viewer) {

        if (!viewers.containsKey(urlDirectory)) {
            viewers.put(urlDirectory, new ArrayList<Viewer>());
        }

        this.viewers.get(urlDirectory).add(viewer);
    }

    public void run() {
        //.....
    }

}
