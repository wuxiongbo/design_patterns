# Introduce Assertion（引⼊断⾔）
某⼀段代码需要对程序状态做出某种假设。


以断⾔明确表现这种假设。
```java
double getExpenseLimit() {
    // should have either expense limit or a primary project
    return (_expenseLimit != NULL_EXPENSE) ? 
            _expenseLimit : _primaryProject.getMemberExpenseLimit();
}
```

```java
double getExpenseLimit() {
    
    assert (_expenseLimit != NULL_EXPENSE || _primaryProject != null) :
            "Expense limit and primary project are both missing";
    
    return (_expenseLimit != NULL_EXPENSE) ? 
            _expenseLimit : _primaryProject.getMemberExpenseLimit();
}
```


## 动机

常常会有这样⼀段代码：只有当某个条件为真时，该段代码才能正常运⾏。
例如 平⽅根计算只对正值才能进⾏，
⼜例如 某个对象可能假设其字段⾄少有⼀个不等于null。

这样的假设通常并没有在代码中明确表现出来，你必须阅读整个算法才能看出。 
有时，程序员会以注释写出这样的假设。
⽽我要介绍的是⼀种更好的技术：使⽤断⾔明确标明这些假设。

断⾔是⼀个条件表达式，应该总是为真。
如果它失败，表示程序员犯了错误。
因此，断⾔的失败应该导致⼀个⾮受控异常（unchecked exception）。
断⾔绝对不能被系统的其他部分使⽤。

实际上，程序最后的成品，往往将断⾔统统删除。
因此，标记 “某些东⻄是个断⾔”是很重要的。

断⾔可以作为交流与调试的辅助。
在交流的⻆度上，断⾔可以帮助程序阅读者理解代码所做的假设：在调试的⻆度上，断⾔可以在距离bug段近的地⽅抓住它们。 
当我编写⾃我测试代码的时候发现，断⾔在调试⽅⾯的帮助变得不那么重要了，但我仍然⾮常看重它们在交流⽅⾯的价值。

## 做法

如果程序员不犯错，断⾔就应该不会对系统运⾏造成任何影响，所以加⼊断⾔永远不会影响程序的⾏为。

⼝ 如果你发现代码假设某个条件始终为真，就加⼊⼀个断⾔明确说明这种情况。

你可以新建⼀个Assert类，⽤于处理各种情况下的断⾔.


注意，不要滥⽤断⾔。
请不要使⽤它来检查“你认为应该为真”的条件，请只使⽤它来检查“⼀定必须为真”的条件。
滥⽤断⾔可能会造成难以维护的重复逻辑。 
在⼀段逻辑中加⼊断⾔是有好处的，因为它迫使你重新考虑这段代码的约束条件。 
如果不满⾜这些约束条件，程序也可以正常运⾏，断⾔就不会带给你任何帮助，只会把代码变得混乱，
并且有可能妨碍以后的修改。

你应该常常问⾃⼰：
如果断⾔所指示的约束条件不能满⾜，代码是否仍能正常运⾏？如果可以，就把断⾔拿掉。

另外，还需要注意断⾔中的重复代码。它们和其他任何地⽅的重复代码⼀样不好闻。
你可以⼤胆使⽤ Extract Method （110）去掉那些重复代码。

## 范例

下⾯是⼀个简单例⼦：开⽀限制。
后勤部门的员⼯每个⽉有固定的开⽀限额： 
业务部门的员⼯则按照项⽬的开⽀限额来控制⾃⼰的开⽀。
⼀个员⼯可能没有开⽀额度可⽤，也可能没有参与项⽬，但两者总要有⼀个（否则就没有经费可⽤了）。
在 开⽀限额相关程序中，上述假设总是成⽴的，因此：
```java
class Employee {
    private static final double NULL_EXPENSE = -1.0;
    private double _expenseLimit = NULL_EXPENSE;
    private Project _primaryProject;
    
    double getExpenseLimit() {
        return (_expenseLimit != NULL_EXPENSE) ? 
                _expenseLimit : _primaryProject.getMemberExpenseLimit();
    }
    boolean withinLimit(double expenseAmount){
        return (expenseAmount <= getExpenseLimit());
    }
}
```


这段代码包含了⼀个明显假设：
任何员⼯，要么参与某个项⽬，要么有个⼈开⽀限额。
我们可以使⽤断⾔在代码中更明确地指出这⼀点：
```java
double getExpenseLimit() {
    assert (_expenseLimit != NULL_EXPENSE || _primaryProject != null) :
            "Expense limit and primary project are both missing";
    return (_expenseLimit != NULL_EXPENSE) ? 
            _expenseLimit : _primaryProject.getMemberExpenseLimit();
}
```

这条断⾔不会改变程序的任何⾏为。
另⼀⽅⾯，如果断⾔中的条件不为真，我就会收到⼀个运⾏期异常：
也许是在 withinLimit()函数中抛出⼀个空指针异常，
也许是在Assert.isTrue()函数中抛出⼀个运⾏期异常。
有时断⾔可以帮助程序员找到bug，因为它离出错地点很近。
但是，更多时候，断⾔的价值在于：帮助程序员理解代码正确运⾏的必要条件。


我常对断⾔中的条件表达式使⽤ Extract Method （110），
也许是为了 将若⼲地⽅的重复码 提炼到同⼀个函数中，
也许只是为了 更清楚说明条件表达式的⽤途。

断⾔可被轻松拿掉，所以它们不可能影响最终成品的性能。
编写⼀个辅助类（例如 Assert类）当然有所帮助，可惜的是断⾔参数中的任何表达式不论什么情况都⼀定会被执⾏⼀遍。
阻⽌它的唯⼀办法就是使⽤类似下⾯的⼿法：
```java
double getExpenseLimit() {
    Assert.isTrue(Assert.ON && (_expenseLimit != NULL_EXPENSE || _primaryProject != null));
    return (_expenseLimit != NULL_EXPENSE) ? 
            _expenseLimit : _primaryProject.getMemberExpenseLimit();
}

```

或者是这种⼿法：
```java
double getExpenseLimit() {
    if (Assert.ON) {
        Assert.isTrue(_expenseLimit != NULL_EXPENSE || _primaryProject != null);
    }
    return (_expenseLimit != NULL_EXPENSE) ? 
            _expenseLimit : _primaryProject.getMemberExpenseLimit();
}
```

如果Assert.ON是个常量，编译器就会对它进⾏检查：
如果它等于false，就不再执⾏条件表达式后半段代码。

但是，加上这条语句实在有点丑陋，所以很多程序员宁可 仅仅使⽤ Assert.isTrue()函数，
然后，在项⽬结束前以过滤程序滤掉使⽤断言的每⼀⾏代码（可以使⽤Perl之类的语言来编写这样的过滤程序）。

Assert类 应该有多个函数，函数名称应该帮助程序员理解其功⽤。
除了 isTrue() 之外，你还可以为它加上 equals() 和 shouldNeverReachHere() 等函数。

