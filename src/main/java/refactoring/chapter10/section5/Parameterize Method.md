# Parameterize Method（令函数携带参数）




## 做法

⼝ 新建⼀个带有参数的函数，使它可以替換先前所有的重复性函数。
⼝ 编译。
⼝ 将调⽤旧函数的代码改为调⽤新函数。
⼝ 编译，测试。
⼝ 对所有旧函数重复上述步骤，每次替换后，修改并测试。

也许你会发现，你⽆法⽤这种办法处理整个函数，但可以处理函数中的⼀部分代码。
这种情况下，你应该⾸先将这部分代码提炼到⼀个独⽴函数中，
然后，再对那个 提炼所得的函数，使⽤ Parameterize Method（283）。

## 范例

下⾯是⼀个最简单的例⼦：
```java
class Employee {
    
    void tenPercentRaise(){
        salary *= 1.1;
    }
    
    void fivePercentRaise(){
        salary *= 1.05;
    }
}
```

这段代码可以替换如下：
```java
class Employee {
    
    void raise(double factor){
        salary *= (1 + factor);
    }
}

```
当然，这个例⼦实在太简单了，所有⼈都能做到。

下⾯是⼀个稍微复杂的例⼦：

```java

public Dollars baseCharge(){
    double result = Math.min(lastUsage(), 100) * 0.03;
    
    if (lastUsage() > 100) {
        result += (Math.min(lastUsage(), 200) - 100) * 0.05;
    }
    
    if (lastUsage() > 200) {
        result += (lastUsage() - 200) * 0.07;
    }
    
    return new Dollars(result);
}

```

上述代码可以替换如下：
```java
public Dollars baseCharge(){
    double result = usageInRange(0, 100) * 0.03;
    result += usageInRange(100, 200) * 0.05;
    result += usageInRange(200, Integer.MAX_VALUE) * 0.07;
    return new Dollars(result);
}

// 查询函数
public int usageInRange(int start, int end){
    if (lastUsage() > start) {
        return Math.min(lastUsage(), end) - start;
    } else {
        return 0;
    }
}

```

本项重构的要点在于：以“可将少量数值视为参数”为依据，找出带有重复性的代码。

