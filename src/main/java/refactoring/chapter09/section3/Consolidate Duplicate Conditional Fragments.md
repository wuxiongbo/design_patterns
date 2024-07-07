# Consolidate Duplicate Conditional Fragments（合并重复的条件⽚段）

在条件表达式的每个分⽀上有着相同的⼀段代码。 


将这段重复代码搬移到条件表达式之外。
```java
public void function1() {
    if (isSpecialDeal()) {
        total = price * 0.95;
        // First call
        send();
    } else {
        total = price * 0.98;
        // Second call
        send();
    }
}
```

```java
public void function1() {
    if (isSpecialDeal()) {
        total = price * 0.95;
    } else {
        total = price * 0.98;
    }
    // 将重复代码搬移到条件表达式之外
    send();
}
```

## 动机
有时你会发现，⼀组条件表达式的所有分⽀都执⾏了相同的某段代码。
如果是这样，你就应该将这段代码搬移到条件表达式外⾯。
这样，代码才能更清楚地表明哪些东⻄随条件的变化⽽变化、哪些东⻄保持不变。


## 做法
⼝ 鉴别出 “执⾏⽅式不随条件变化⽽变化”的代码。
⼝ 如果这些共通代码位于条件表达式起始处，就将它移到条件表达式之前。 
⼝ 如果这些共通代码位于条件表达式尾端，就将它移到条件表达式之后。
⼝ 如果这些共通代码位于条件表达式中段，就需要观察共通代码之前或之后的 代码是否改变了什么东⻄。
   如果的确有所改变，应该⾸先将共通代码向前或 向后移动，移⾄条件表达式的起始处或尾端，再以前⾯所说的办法来处理。
⼝ 如果共通代码不⽌⼀条语句，应该⾸先使⽤ Extract Method （110）将共通代码提炼到⼀个独⽴函数中，再以前⾯所说的办法来处理。

## 范例
你可能遇到这样的代码：

```java
public void function1() {
    if (isSpecialDeal()) {
        total = price * 0.95;
        send();
    } else {
        total = price * 0.98;
        send();
    }
}
```
由于条件表达式的两个分⽀都执⾏了send（）函数，所以我应该将send（）移到 条件表达式的外围：
```java
public void function1() {
    if (isSpecialDeal()) {
        total = price * 0.95;
    } else {
        total = price * 0.98;
    }
    // 将重复代码搬移到条件表达式之外
    send();
}
```

我们也可以使⽤同样的⼿法来对待异常。

如果在 try区段内 可能引发 异常的语句 之后，以及所有 catch区段之内，都重复执⾏了同⼀段代码，
就可以将这段重复代码移到 final区段。

