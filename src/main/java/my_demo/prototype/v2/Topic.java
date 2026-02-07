package my_demo.prototype.v2;

import lombok.Data;

import java.util.HashMap;

/**
 * @author Xander Wu
 * @date 2023/12/5 09:59
 */
@Data
public class Topic implements Cloneable{
    private final HashMap<String, String> option;
    private final String key;

    public Topic(HashMap<String, String> option, String key) {
        this.key = key;
        this.option = option;
    }

    @Override
    public Topic clone() {
        try {
            Topic clone = (Topic) super.clone();

            // TODO: copy mutable state here, so the clone can't change the internals of the original

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
