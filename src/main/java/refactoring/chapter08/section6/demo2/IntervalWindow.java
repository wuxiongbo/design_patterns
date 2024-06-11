package refactoring.chapter08.section6.demo2;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * 视图层（观察者）
 * @author bear
 * @date 2024/6/12 上午12:26
 * @description
 */
public class IntervalWindow extends Frame implements Observer {

    java.awt.TextField startField;
    java.awt.TextField endField;
    java.awt.TextField lengthField;

    private Interval subject;

    public IntervalWindow() {

        startField = new java.awt.TextField();
        endField = new java.awt.TextField();
        lengthField = new java.awt.TextField();

        // 注册监听事件
        SymFocus focusListener = new SymFocus();
        startField.addFocusListener(focusListener);
        endField.addFocusListener(focusListener);
        lengthField.addFocusListener(focusListener);

        // 被观察者
        subject = new Interval();
        subject.addObserver(this);
        update(subject, null);
    }


    class SymFocus extends java.awt.event.FocusAdapter {

        public void focusLost(java.awt.event.FocusEvent event) {
            Object object = event.getSource();
            if (object == startField) {
                StartField_FocusLost(event);
            } else if (object == endField) {
                EndField_FocusLost(event);
            } else if (object == lengthField) {
                LengthField_FocusLost(event);
            }
        }

        // 失焦事件（赋值给领域类，领域类反向通知本GUI）
        void StartField_FocusLost(java.awt.event.FocusEvent event) {
            setStart(startField.getText());
            if (isNotInteger(getStart())) {
                setStart("0");
            }
            subject.calculateLength();
        }

        // 失焦事件（赋值给领域类，领域类反向通知本GUI）
        void EndField_FocusLost(java.awt.event.FocusEvent event) {
            setEnd(endField.getText());
            if (isNotInteger(getEnd())) {
                setEnd("0");
            }
            subject.calculateLength();
        }

        // 失焦事件（赋值给领域类，领域类反向通知本GUI）
        void LengthField_FocusLost(java.awt.event.FocusEvent event) {
            setLength(lengthField.getText());
            if (isNotInteger(getLength())) {
                setLength("0");
            }
            subject.calculateEnd();
        }

        private boolean isNotInteger(String text) {
            try {
                Integer.parseInt(text);
                return false;
            } catch (NumberFormatException e) {
                return true;
            }
        }
    }

    // 通知的消费行为（根据领域类，初始化当前GUI）
    @Override
    public void update(Observable observed, Object arg) {
        endField.setText(subject.getEnd());
        startField.setText(subject.getStart());
        lengthField.setText(subject.getLength());
    }

    String getEnd() {
        return subject.getEnd();
    }

    void setEnd(String arg) {
        subject.setEnd(arg);
    }

    String getStart() {
        return subject.getStart();
    }

    void setStart(String arg) {
        subject.setStart(arg);
    }

    String getLength() {
        return subject.getLength();
    }

    void setLength(String arg) {
        subject.setLength(arg);
    }
}
