package design_patterns.chapter43.demo1.dependence;

import design_patterns.chapter43.demo1.v2.IdGenerator;

/**
 * <p>描述类的信息</p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2021/12/15
 * </pre>
 */
public class SharedObjectStorage {
    public IdGenerator load(Class<IdGenerator> idGeneratorClass) {
        return null;
    }

    public void save(IdGenerator idGenerator, Class<IdGenerator> idGeneratorClass) {

    }
}
