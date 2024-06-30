# Replace Nested Conditional with Guard Clauses （以卫语句取代嵌套条件表达式）

函数中的条件逻辑使⼈难以看清正常的执⾏路径。


使⽤ **卫语句** ，表现所有特殊情况。
```java
public void getPayAmount() {
    double result;
    if (_isDead) {
        result = deadAmount();
    } else {
        if (_isSeparated) {
            result = separatedAmount();
        } else {
            if (_isRetired) {
                result = retiredAmount();
            } else {
                result = normalPayAmount();
            }
        }
    }
}
```

```java
public void getPayAmount() {
    if (_isDead) {
        return deadAmount();
    }
    if (_isSeparated) {
        return separatedAmount();
    }
    if (_isRetired) {
        return retiredAmount();
    }
    return normalPayAmount();
}
```

## 动机

根据我的经验，条件表达式通常有两种表现形式。
第⼀种形式是：所有分⽀都属于正常⾏为。
第⼆种形式则是：条件表达式提供的答案中只有⼀种是正常⾏为，其他都是不常⻅的情况。

这两类条件表达式有不同的⽤途，这⼀点应该通过代码表现出来。
如果两条分⽀都是正常⾏为，就应该使⽤形如 if..else.. 的条件表达式;
如果某个条件极其罕⻅，就应该单独检查该条件，并在该条件为真时，⽴刻从函数中返回。
这样的单独检查常常被称为“卫语句”（guard clauses）［Beck］。

Replace Nested Conditional with Guard Clauses（250）的精髓就是：给某⼀条分⽀以特别的重视。  
如果使⽤if-then-else结构，你对 if分⽀ 和 else分⽀ 的重视是同等的。  
这样的代码结构传递给阅读者的消息就是：各个分⽀有同样的重要性。  
**卫语句**就不同了，它告诉阅读者：“这种情况很罕⻅，如果它真地发⽣了，请做⼀些必要的整理⼯作，然后退出。”

“每个函数只能有⼀个⼊⼝和⼀个出⼝”的观念，根深蒂固于某些程序员的脑海⾥。
我发现，当我处理他们编写的代码时，经常需要使⽤ Replace Nested Conditional with Guard Clauses （250）。
现今的编程语⾔都会强制保证每个函数只有⼀个⼊⼝，⾄于“单⼀出⼝”规则，其实不是那么有⽤。

在我看来，保持代码清晰才是最关键的：  
如果，单⼀出⼝ 能使这个函数更清楚易读，那么就使⽤单⼀出⼝；
否则，就不必这么做。

## 做法

-[ ] 对于每个检查，放进⼀个卫语句。
>卫语句，要不就 从函數中返回，要不就 抛出⼀个异常

-[ ] 每次将条件检查替换成卫语句后，编译并测试。
> 如果所有卫语句都导致相同结果，请使⽤ Consolidate Conditional Expressions （240）

## 范例

想象⼀个薪册系统，其中以特殊规则处理 死亡员⼯、驻外员⼯、退休员⼯的薪资。
这些情况不常有，但的确偶⽽会出现。

假设我在这个系统中看到下列代码：
```java
public void getPayAmount() {
    double result;
    if (_isDead) {
        result = deadAmount();
    } else {
        if (_isSeparated) {
            result = separatedAmount();
        } else {
            if (_isRetired) {
                result = retiredAmount();
            } else {
                result = normalPayAmount();
            }
        }
    }
}
```

在这段代码中，⾮正常情况的检查 掩盖了 正常情况的检查，所以应该⽤ `卫语句` 来取代这些检查，以提⾼程序清晰度。
我可以逐⼀引⼊卫语句。
让我们从最上⾯的 条件检查动作开始：
```java
public double getPayAmount() {
    double result;
    if (_isDead) {
        // 引⼊卫语句
        return deadAmount();
    } else {
        if (_isSeparated) {
            result = separatedAmount();
        } else {
            if (_isRetired) {
                result = retiredAmount();
            } else {
                result = normalPayAmount();
            }
        }
    }
}
```

然后，继续下去，仍然⼀次替换⼀个检查动作：
```java
public double getPayAmount() {
    double result;
    if (_isDead) {
        return deadAmount();
    }
    if (_isSeparated) {
        // 继续引⼊卫语句
        return separatedAmount();
    } else {
        if (_isRetired) {
            result = retiredAmount();
        } else {
            result = normalPayAmount();
        }
    }
}

```

然后是最后⼀个，此时，result变量已经没有价值了，所以我把它删掉：
```java
public double getPayAmount() {
    if (_isDead) {
        return deadAmount();
    }
    if (_isSeparated) {
        return separatedAmount();
    }
    if (_isRetired) {
        // 最后⼀个卫语句
        return retiredAmount();
    }
    return normalPayAmount();
}

```

嵌套条件代码 往往由那些深信 “每个函数只能有⼀个出⼝”的程序员写出。
我发现那条规则实在有点太简单粗暴了。
如果对函数剩余部分不再有兴趣，当然应该⽴刻退出。
引导阅读者去看⼀个没有⽤的 else区段，只会妨碍他们的理解。


## 范例：将条件反转

审阅本书初稿时，Joshua Kerievsky指出：
你常常可以将条件表达式反转，从⽽实现 Replace Nested Conditional with Guard Clauses （250）。

为了拯救我可怜的想象⼒，他还好⼼帮我想了个例⼦：

```java
public double getAdjustedCapital() {
    double result = 0.0;
    if (_capital > 0.0) {
        if (_intRate > 0.0 && _duration > 0.0) {
            result = (_income / _duration) * ADJ_FACTOR;
        }
    }
    return result;
}

```
同样地，我逐⼀进⾏替换。
不过这次在插⼊卫语句时，我需要将相应的条件反转过来：
```java
public double getAdjustedCapital() {
    double result = 0.0;
    if (_capital <= 0.0) {
        return result;
    }
    if (_intRate > 0.0 && _duration > 0.0) {
        result = (_income / _duration) * ADJ_FACTOR;
    }
    return result;
}
```

下⼀个条件稍微复杂⼀点，所以我分两步进⾏逆反。
⾸先加⼊⼀个逻辑⾮操作：
```java
public double getAdjustedCapital() {
    double result = 0.0;
    if (_capital <= 0.0) {
        return result;
    }
    
    if (!(_intRate <= 0.0 || _duration <= 0.0)) {
        result;
    }
    result = (_income / _duration) * ADJ_FACTOR;
    return result;
}
```
但是在这样的条件表达式中留下⼀个逻辑⾮，会把我的脑袋拧成⼀团乱麻，所以我把它简化成下⾯这样：
```java
public double getAdjustedCapital() {
    double result = 0.0;
    if (_capital <= 0.0) {
        return result;
    }
    if (_intRate <= 0.0 || _duration <= 0.0) {
        return result;
    }
    result = (_income / _duration) * ADJ_FACTOR;
    return result;
}
```

这时候，我⽐较喜欢在卫语句内返回⼀个明确值，因为这样我可以⼀⽬了然地看到卫语句返回的失败结果。
此外，这种时候我也会考虑使⽤Replace Magic Number with Symbolic Constant（204）。
```java
public double getAdjustedCapital() {
    double result;
    if (_capital <= 0.0) {
        return 0.0;
    }
    if (_intRate <= 0.0 || _duration <= 0.0) {
        return 0.0;
    }
    result = (_income / _duration) * ADJ_FACTOR;
    return result;
}
```

完成替换之后，我同样可以将临时变量移除：
```java
public double getAdjustedCapital() {
    if (_capital <= 0.0) {
        return 0.0;
    }
    if (_intRate <= 0.0 || _duration <= 0.0) {
        return 0.0;
    }
    return (_income / _duration) * ADJ_FACTOR;
}
```
 
