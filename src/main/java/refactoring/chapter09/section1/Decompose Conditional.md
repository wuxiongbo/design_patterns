# Decompose Conditional（分解条件表达式）

你有⼀个复杂的条件（if-then-else） 语句。

从 if、then、else 三个段落中分别提炼出独⽴函数。
```java
public static void main(String[] args) {
    if (date.before(SUMMER_START) || date.after(SUMMER_END)) {
        charge = quantity * _winterRate + _winterServiceCharge;
    } else {
        charge = quantity * _summerRate;
    }
}

```

```java
public static void main(String[] args) {
    if (notSummer(date)) {
        charge = winterCharge(quantity);
    } else {
        charge = summerCharge(quantity);
    }
}
```

## 动机
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
程序之中，复杂的条件逻辑是最常导致复杂度上升的地点之⼀。
你必须编写代码来检查不同的条件分⽀、根据不同的分⽀做不同的事，然后，你很快就会得到⼀个相当⻓的函数。
⼤型函数⾃身就会使代码的可读性下降，⽽条件逻辑则会使代码更难阅读。
在带有复杂条件逻辑的函数中，代码（包括 检查条件分⽀的代码 和 真正实现功能的代码）会告诉你发⽣的事，
但常常让你弄不清楚为什么会发⽣这样的事，这就说明代码的可读性的确⼤⼤降低了。  

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
和任何⼤块头代码⼀样，你可以将它分解为多个独⽴函数，根据每个⼩块代码的⽤途，为分解⽽得的新函数命名，并将原函数中对应的代码改为调⽤新建函数，从⽽更清楚地表达⾃⼰的意图。
对于条件逻辑，将每个分⽀条件 分解成 新函数 还可以给你带来更多好处：
可以突出条件逻辑，更清楚地表明每个分⽀的作⽤，并且突出每个分⽀的原因。

## 做法

-[ ] 将 if段落 提炼出⽶，构成⼀个独⽴函数。
-[ ] 将 then段落 和 else段落 都提炼出来，各⾃构成⼀个独⽴函数。 
如果发现嵌套的条件逻辑，我通常会先观察是否可以使⽤ Replace Nested Conditional with Guard Clauses（250）。
如果不⾏，才开始分解其中的每个条件。


## 范例

假设我要计算购买某样商品的总价（总价=数量X单价），⽽这个商品在 冬季 和 夏季 的单价，是不同的：
```java
public void function1() {
    if (date.before(SUMMER_START) || date.after(SUMMER_END)) {
        charge = quantity * _winterRate + _winterServiceCharge;
    } else {
        charge = quantity * _summerRate;
    }
}

```

我把每个分⽀的判断条件都提炼到⼀个独⽴函数中，如下所示：
```java
public void function1() {
    if (notSummer(date)) {
        charge = winterCharge(quantity);
    } else {
        charge = summerCharge(quantity);
    }
}

private boolean notSummer(Date date) {
    return date.before(SUMMER_START) || date.after(SUMMER_END);
}

private double summerCharge(int quantity) {
    return quantity * _summerRate;
}

private double winterCharge(int quantity) {
    return quantity * _winterRate + _winterServiceCharge;
}

```

通过这段代码可以看出整个重构带来的清晰性。
实际⼯作中，我会逐步进⾏每⼀次提炼，并在每次提炼之后编译并测试。
像这样的情况下，许多程序员都不会去提炼分⽀条件。
因为这些分⽀条件往往⾮常短，看上去似乎没有提炼的必要。
但是，尽管这些条件往往很短，在代码意图和代码⾃身之间往往存在不⼩的差距。
哪怕在上⾯这样⼀个⼩⼩例⼦中，`notSummer(date)` 这个语句也能够⽐原本的代码更好地表达⾃⼰的⽤途。
对于原来的代码，我必须看着它，想⼀想，才能说出其作⽤。
当然，在这个简单的例⼦中，这并不困难。
不过，即使如此，提炼出来的函数可读性也更⾼⼀些————它看上去就像⼀段注释那样清楚⽽明⽩。

