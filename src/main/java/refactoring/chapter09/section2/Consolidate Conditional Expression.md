# Consolidate Conditional Expression（合并条件表达式）
你有⼀系列条件测试，都得到相同结果。 

将这些测试合并为⼀个条件表达式，并将这个条件表达式提炼成为⼀个独⽴函数。

```java
double disabilityAmount() {
    if (_seniority < 2) return 0;
    if (_monthsDisabled > 12) return 0;
    if (_isPartTime) return 0;
    // compute the disability amount
}
```

```java
double disabilityAmount() {
    if (isNotEligibleForDisability()) return 0;
    // compute the disability amount
}
```

## 动机

有时你会发现这样⼀串条件检查：检查条件各不相同，最终⾏为却⼀致。
如果发现这种情况，就应该使⽤ “逻辑或” 和 “逻辑与” 将它们合并为⼀个条件表达式。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
之所以要合并条件代码，有两个重要原因。
⾸先，合并后的条件代码会告诉你“实际上只有⼀次条件检查，只不过有多个并列条件需要检查⽽已”，从⽽使这⼀次检查的⽤意更清晰。
当然，合并前和合并后的代码有着相同的效果，但原先代码传达出的信息却是 “这⾥有⼀些各⾃独⽴的条件测试，它们只是恰好同时发⽣”。
其次，这项重构往往可以为你使⽤ Extract Method （110）做好准备。
将检查条件提炼成⼀个独⽴函数 对于理清代码意义⾮常有⽤，因为它把描述“做什么”的语句换成了“为什么这样做”。 
条件语句的合并理由也同时指出了不要合并的理由：如果你认为这些检查的确 彼此独⽴，的确不应该被视为同⼀次检查，那么就不要使⽤本项重构。因为在这种 情况下，你的代码已经清楚表达出⾃⼰的意义。 9.2 Consolidate Conditional Expression（合并条件表达式）

## 做法
-[ ] 确定这些条件语句都没有副作⽤。
>如果条件表达式有副作⽤，你就不能使⽤本项重构。 
-[ ] 使⽤适当的逻掛操作符，将⼀系列相关条件表达式合并为⼀个。
-[ ] 编译，测试。
-[ ] 对合并后的条件表达式实施 Extract Method （110）。


## 范例：使⽤逻辑或

请看下列代码：
```java
double disabilityAmount() {
    if (_seniority < 2) return 0;
    if (_monthsDisabled > 12) return 0;
    if (_isPartTime) return 0;
    // compute the disability amount
}

```


在这段代码中，我们看到⼀连串的条件检查，它们都做同⼀件事。
对于这样的代码，上述条件检查 等价于 ⼀个以 逻辑或 连接起来的语句： 
```java
double disabilityAmount() {
    if (_seniority < 2 || _monthsDisabled > 12 || _isPartTime) return 0;
    // compute the disability amount
}
```
现在，我可以观察这个新的条件表达式，并运⽤Extract Method （110）将它提炼成⼀个独⽴函数，以函数名称表达该语句所检查的条件：
```java
double disabilityAmount() {
    if (isNotEligibleForDisability()) return 0;
    // compute the disability amount
}

boolean isNotEligibleForDisability() {
    return (_seniority < 2 || _monthsDisabled > 12 || _isPartTime);
}
```

## 范例：使⽤逻辑与

上述实例展示了逻辑或的⽤法。下列代码展示逻辑与的⽤法：
```java
public double function1() {
    if (onVacation()) {
        if (lengthofService() > 10) {
            return 1;
        }
    }
}

```

这段代码可以变成：
```java
public double function1() {
    if (onVacation() && lengthofService() > 10) {
        return 1;
    }
}

```
你可能还会发现，某些情况下，需要同时使⽤ 逻辑或、逻辑与 和 逻辑⾮，量终得到的条件表达式可能很复杂，
所以，我会先使⽤ Extract Method （110）将表达式的⼀部分提炼出来，从⽽使整个表达式变得简单⼀些。 
如果我所观察的部分只是 对条件进⾏检查 并 返回⼀个值，就可以使⽤ 三元操作符 将这⼀部分 变成⼀条return语句。
因此，下列代码：
```java
public double function1() {
    if (onVacation() && lengthofService() > 10) {
        return 1;
    } else {
        return 0.5;
    }
}


```

就变成了：
```java
public double function1() {
    return (onVacation() && lengthofService() > 10) ? 1 : 0.5;
}

```