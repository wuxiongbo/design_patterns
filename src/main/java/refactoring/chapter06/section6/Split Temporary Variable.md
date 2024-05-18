# 分解临时变量

临时变量有各种不同⽤途，其中某些⽤途会很⾃然地导致临时变量被多次赋值。

“循环变量” 和 “结果收集变量” 就是两个典型例⼦：
- 循环变量（loop variable）会随循环的每次运⾏⽽改变（例如: `for(int i=0; i<10; i++)` 语句中的i )；
- 结果收集变量（collecting temporary variable）负责将“通过整个函数的运算” ⽽构成的某个值收集起来。

除了这两种情况，还有很多临时变量⽤于保存⼀段冗⻓代码的运算结果，以便稍后使⽤。  
这种**临时变量**应该只被赋值⼀次。  
如果它们被赋值超过⼀次，就意味它们在函数中承担了⼀个以上的责任。  
如果临时变量承担多个责任，它就应该被替换（分解）为多个临时变量，每个变量只承担⼀个责任。
同⼀个临时变量承担两件不同的事情，会令代码阅读者糊涂。

```java
class demo {
    private double _height;
    private double _width;

    void test() {
        // 周长
        double temp = 2 *(_height +_width);
        System.out.println(temp);

        // 面积
        temp = 2 *(_height * _width);
        System.out.printin(temp);
    }
}
```

某个临时变量被赋值超过一次，它既不是循环变量，也不是用于收集计算结果。
针对每次赋值，创造一个独立、对应的临时变量，每个临时变量只承担一个责任。
```java
class demo {
    private double _height;
    private double _width;

    void test() {
        // 周长
        double perimeter = 2 *(_height +_width);
        System.out.println(perimeter);

        // 面积
        double area  = 2 *(_height * _width);
        System.out.printin(area);
    }
}
```

