package refactoring.chapter08.section6.demo2;

import java.util.Observable;
import java.util.Observer;

/**
 * 领域对象（包括：领域数据、领域函数）； 这里的数据，是————“被监视数据”
 * <p>
 * 领域 （被观察者）
 *
 * @author bear
 * @date 2024/6/12 上午12:26
 * @description
 */
public class Interval extends Observable {

    private String _end = "0";
    private String _start = "0";
    private String _length = "0";

    String getEnd() {
        return _end;
    }

    /**
     * {@linkplain  IntervalWindow#update(Observable, Object) 通知目标}
     * @param arg
     */
    void setEnd(String arg) {
        _end = arg;

        //将此Observable对象标记为已更改
        setChanged();
        // 通知所有观察者
        notifyObservers();

    }

    String getStart() {
        return _start;
    }

    /**
     * {@linkplain  IntervalWindow#update(Observable, Object) 通知目标}
     * @param arg
     */
    void setStart(String arg) {
        _start = arg;

        //将此Observable对象标记为已更改
        setChanged();
        // 通知所有观察者
        notifyObservers();

    }

    String getLength() {
        return _length;
    }

    /**
     * {@linkplain  IntervalWindow#update(Observable, Object) 通知目标}
     * @param arg
     */
    void setLength(String arg) {
        _length = arg;

        //将此Observable对象标记为已更改
        setChanged();
        // 通知所有观察者
        notifyObservers();
    }


    /**
     * 计算长度值
     * <p>
     * 我的任务就是将与展现⽆关的计算逻辑从GUI中分离出来。
     * 基本上这就意味将 calculateLength（）和calculateEnd（）移到⼀个独⽴的领域类去。
     * 为了这⼀⽬的，我需要能够在不引⽤窗⼝类的前提下获取 Start、End和 Length三个⽂本框的值。
     * 唯⼀办法就是，将这些数据复制到 领域类 中，并保持与 GUI类 数据同步。
     * 这就是 Duplicate Observed Data （189）的任务。
     *
     */
    void calculateLength() {
        try {
            int start = Integer.parseInt(getStart());
            int end = Integer.parseInt(getEnd());
            int length = end - start;

            setLength(String.valueOf(length));

        } catch (NumberFormatException e) {
            throw new RuntimeException("Unexpected Number Format Error");
        }
    }

    /**
     * 计算结束值
     */
    void calculateEnd() {
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
     *
     * @param o an observer to be added.
     */
    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
    }
}