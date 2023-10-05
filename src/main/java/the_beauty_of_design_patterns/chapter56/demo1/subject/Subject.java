package the_beauty_of_design_patterns.chapter56.demo1.subject;

import the_beauty_of_design_patterns.chapter56.demo1.observer.Observer;
import the_beauty_of_design_patterns.chapter56.dependence.Message;

/**
 * <p> 主题 </p>
 *
 * <pre>
 * @author wuxiongbo
 * @date 2022/1/10
 * </pre>
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Message message);
}
