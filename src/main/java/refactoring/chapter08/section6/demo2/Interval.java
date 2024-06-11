package refactoring.chapter08.section6.demo2;

import java.util.Observable;
import java.util.Observer;

/**
 * 领域 （被观察者）
 * @author bear
 * @date 2024/6/12 上午12:26
 * @description
 */
public class Interval extends Observable {

    private String end = "0";
    private String start = "0";
    private String length = "0";

    String getEnd () {
        return end;
    }

    void setEnd (String arg){
        end = arg;
        //将此Observable对象标记为已更改
        setChanged();
        // 通知所有观察者
        notifyObservers();
    }

    String getStart () {
        return start;
    }
    void setStart (String arg){
        start = arg;
        //将此Observable对象标记为已更改
        setChanged();
        // 通知所有观察者
        notifyObservers();
    }

    String getLength () {
        return length;
    }
    void setLength (String arg){
        length = arg;
        //将此Observable对象标记为已更改
        setChanged();
        // 通知所有观察者
        notifyObservers();
    }

    void calculateLength () {
        try {
            int start = Integer.parseInt(getStart());
            int end = Integer.parseInt(getEnd());
            int length = end - start;
            setLength(String.valueOf(length));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Unexpected Number Format Error");
        }
    }

    void calculateEnd () {
        try {
            int start = Integer.parseInt(getStart());
            int length = Integer.parseInt(getLength());
            int end = start + length;
            setEnd(String.valueOf(end));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Unexpected Number Format Error");
        }
    }

    /**
     * 注册观察者
     * @param o  an observer to be added.
     */
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
    }
}