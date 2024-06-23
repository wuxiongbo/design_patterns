package refactoring.chapter08.section6.demo2;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * 范例
 * <p>
 * GUI对象   Graphical User Interface
 * 视图层（观察者）
 *
 * @author bear
 * @date 2024/6/12 上午12:26
 * @description
 */
public class IntervalWindow extends Frame implements Observer {

    java.awt.TextField _startField;
    java.awt.TextField _endField;
    java.awt.TextField _lengthField;

    /**
     * IntervalWindow类 需要与此崭新的 领域类 建⽴⼀个关联：
     */
    private final Interval _subject;

    public String getEnd() {
        return _subject.getEnd();
    }

    public void setEnd(String arg) {
        _subject.setEnd(arg);
    }

    String getStart() {
        return _subject.getStart();
    }

    void setStart(String arg) {
        _subject.setStart(arg);
    }

    String getLength() {
        return _subject.getLength();
    }

    void setLength(String arg) {
        _subject.setLength(arg);
    }


    public IntervalWindow() {
        // 初始化GUI的文本字段
        _startField = new java.awt.TextField();
        _endField = new java.awt.TextField();
        _lengthField = new java.awt.TextField();

        // 注册事件监听器
        SymFocus focusListener = new SymFocus();
        _startField.addFocusListener(focusListener);
        _endField.addFocusListener(focusListener);
        _lengthField.addFocusListener(focusListener);

        // 我需要合理地初始化_subject字段，并把 IntervalWindows 变成 Interval 的⼀个 Observer(观察者)。
        // 这很简单，只需把下列三行代码放进 IntervalWindows 构造函数 中就可以了：
        _subject = new Interval();
        _subject.addObserver(this);
        update(_subject, null);

        // 我喜欢把这段代码放在整个构造过程的最后。
        // 其中，对 update() 的调⽤，可以确保：当我把 GUI的数据复制 到 领域类 后，GUI 将根据 领域类 进⾏初始化。
        // update() 是在 java.util.Observer接⼝ 中声明的，因此，我必须让 IntervalWindows 实现这⼀接⼝：

    }


    /**
     * 对领域对象发送过来的通知，进行消费（获取领域对象中的数据，更新GUI组件）
     *
     * @param observed the observable object.
     * @param arg      an argument passed to the {@link Observable#notifyObservers() notifyObservers} method.
     */
    @Override
    public void update(Observable observed, Object arg) {

        _endField.setText(_subject.getEnd());
        _startField.setText(_subject.getStart());
        _lengthField.setText(_subject.getLength());

    }


    /**
     * 事件监听器
     * <p>
     * <p>
     * 接下来，我把注意⼒转移到⽂本框。
     * ⼀如往常，我每次只改动⼀个字段。为了 卖弄⼀下我的英语能⼒®，我就从End⽂本框开始。
     * 第⼀件要做的事就是实施Self Encapsulate Field （171）。
     * ⽂本框的更新是通过  getText（）和  setText（）两函数实现的，因此我所建⽴的访问函数需要调⽤这两个函数：
     * <pre>{@code
     *  public String getEnd() {
     *      return _subject.getEnd();
     *  }
     *
     *  public void setEnd(String arg) {
     *      _subject.setEnd(arg);
     *  }
     * }</pre>
     * <p>
     * 然后，找出 _endField 的所有引⽤点，将它们替换为适当的访问函数：
     * <pre>{@code
     *
     *  void calculateLength() {
     *      try {
     *          int start = Integer.parseInt(_startField.getText());
     *          int end = Integer.parseInt(getEnd());
     *          int length = end - start;
     *          _lengthField.setText(String.valueOf(length));
     *      } catch (NumberFormatException e) {
     *          throw new RuntimeException("Unexpected Number Format Error"):
     *      }
     *  }
     *
     *  void calculateEnd() {
     *      try {
     *          int start = Integer.parseInt(_startField.getText());
     *          int length = Integer.parseInt(_lengthField.getText());
     *          int end = start + length;
     *          _endField.setText(String.valueOf(end));
     *      } catch (NumberFormatException e) {
     *          throw new RuntimeException("Unexpected Number Format Error");
     *      }
     *  }
     *  void EndField_FocusLost(java.awt.event.FocusEvent event) {
     *      setEnd(_endField.getText());
     *      if (isNotInteger(getEnd())) {
     *          setEnd("0");
     *      }
     *      calculateLength();
     *  }
     * }</pre>
     * 这是 SelfEncapsulate Field （171）的标准过程。
     *
     * <p>
     * 然⽽，当你处理GUI时，情况还更复杂些：
     * ⽤户可以直接（通过GUI）修改⽂本框内容，不必调⽤setEnd()。
     * 因此，我需要在GUI的事件处理函数中调⽤ setEnd()。
     * 这个动作，把 End⽂本框 设定为 其输入的当前值。
     * 当然，这没带来什么影响，但是通过这样的⽅式，可以确保 ⽤户的输⼊ 确实是通过 设值函数 进⾏的：
     * <pre>{@code
     * void EndField_FocusLost(java.awt.event.FocusEvent event) {
     *     setEnd(_endField.getText());
     *     if (isNotInteger(getEnd())) {
     *         setEnd("0");
     *     }
     *     calculateLength();
     * }
     * }</pre>
     * 上述调⽤动作中，我并没有使⽤前⾯的getEnd()取得End⽂本框当前内容，⽽是 直接访问⽂本框( _endField.getText() )。
     * 之所以这样做，是因为，随后的重构将使 getEnd() 从 领域对象 （⽽⾮⽂本框）身上取值。
     * 那时，如果这⾥⽤的是 领域对象的 getEnd()函数，每当⽤户修改⽂本（复制“被监视数据”）框内容，这⾥就会将⽂本框⼜改回原修改前的值。
     * 所以，我必须使⽤直接访问⽂本框的⽅式获取当前值。
     * 现在，我可以编译并测试字段封装后的⾏为了。
     */
    class SymFocus extends java.awt.event.FocusAdapter {

        // 事件处理函数：失焦事件（赋值给领域类，领域类 反向通知 本GUI，对GUI的联动字段进行更新）
        public void focusLost(java.awt.event.FocusEvent event) {
            Object object = event.getSource();
            if (object == _startField) {
                StartField_FocusLost(event);
            } else if (object == _endField) {
                EndField_FocusLost(event);
            } else if (object == _lengthField) {
                LengthField_FocusLost(event);
            }
        }

        // 事件处理函数：失焦事件（赋值给领域类，领域类 反向通知 本GUI，对GUI的联动字段进行更新）
        void StartField_FocusLost(java.awt.event.FocusEvent event) {
            setStart(_startField.getText());
            if (isNotInteger(getStart())) {
                setStart("0");
            }
            _subject.calculateLength();
        }

        // 事件处理函数：失焦事件（赋值给领域类，领域类 反向通知 本GUI，对GUI的联动字段进行更新）
        void EndField_FocusLost(java.awt.event.FocusEvent event) {
            setEnd(_endField.getText());
            if (isNotInteger(getEnd())) {
                setEnd("0");
            }

            _subject.calculateLength();
        }

        // 事件处理函数：失焦事件（赋值给领域类，领域类 反向通知 本GUI，对GUI的联动字段进行更新）
        void LengthField_FocusLost(java.awt.event.FocusEvent event) {
            setLength(_lengthField.getText());
            if (isNotInteger(getLength())) {
                setLength("0");
            }
            _subject.calculateEnd();
        }

        boolean isNotInteger(String text) {
            try {
                Integer.parseInt(text);
                return false;
            } catch (NumberFormatException e) {
                return true;
            }
        }
    }
}
