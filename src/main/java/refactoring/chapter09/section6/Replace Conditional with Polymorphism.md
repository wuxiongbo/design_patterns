# Replace Conditional with Polymorphism （以多态取代条件表达式）

你⼿上有个条件表达式，它根据对象类型的不同⽽选择不同的⾏为。 

将这个条件表达式的每个分⽀放进⼀个⼦类内的覆写函数中，然后将原始函数声明为抽象函数。
```java
double getSpeed() {
    return switch (_type) {
        case EUROPEAN -> getBaseSpeed();
        case AFRICAN -> getBaseSpeed() - getLoadFactor() * _numberOfCoconuts;
        case NORWEGIAN_BLUE -> (_isNailed) ? 0 : getBaseSpeed(_voltage);
        default -> throw new RuntimeException("Should be unreachable");
    };
}
```

```plantuml
abstract class Bird {
    abstract double getSpeed();
}

class European extends Bird {
    double getSpeed();
}

class African extends Bird {
    double getSpeed();
}

class NorwegianBlue extends Bird {
    double getSpeed();
}
```


## 动机

在⾯向对象术语中，听上去最⾼贵的词⾮“多态”莫属。  
多态最根本的好处就是：  
如果你需要根据对象的不同类型⽽采取不同的⾏为，多态使你不必编写明显的条件表达式。
正因为有了多态，所以你会发现：“类型码的switch语句”以及“基于类型名称的if-then-else语句” 在⾯向对象程序中很少出现。
多态能够给你带来很多好处。
如果同⼀组条件表达式在程序许多地点出现，那么使⽤多态的收益是最⼤的。
使⽤条件表达式时，如果你想添加⼀种新类型，就必须查找并更新所有条件表达式。
但如果改⽤多态，只需建⽴⼀个新的⼦类，并在其中提供适当的函数就⾏了。
类的⽤户不需要了解这个⼦类，这就⼤⼤降低了系统各部分之间的依赖，使系统升级更加容易。


## 做法
使⽤ Replace Conditional with Polymorphism （255）之前，⾸先必须有⼀个继承结构。
你可能已经通过先前的重构得到了这⼀结构。如果还没有，现在就需要建⽴它。

要建⽴继承结构，有两种选择：
Replace Type Code with Subclasses （223）和
Replace Type Code with Stare/Strategy （227）。
前⼀种做法⽐较简单，因此应该尽可能使⽤它。
但如果你需要在对象创建好之后修改类型码，就不能使⽤继承⼿法，只能使⽤ State/Strategy 模式。
此外，如果由于其他原因，要重构的类已经有了⼦类，那么也得使⽤ State/Strategy 模式。

记住，如果 若⼲switch语句 针对的是 同⼀个类型码，你只需针对 这个类型码 建⽴⼀个 继承结构 就⾏了。

现在，可以向条件表达式开战了。
你的⽬标可能是switch语句，也可能是if语句。

-[ ] 如果要处理的条件表达式是⼀个更⼤函数中的⼀部分，⾸先对条件表达式进⾏分析，然后使⽤Extract Method（110）将它提炼到⼀个独⽴函数去。 
-[ ] 如果有必要，使⽤Move Method （142）将条件表达式放置到继承结构的顶端。
-[ ] 任选⼀个⼦类，在其中建⽴⼀个函数，使之覆写超类中容纳条件表达式的那 个函数。
   将与该⼦类相关的条件表达式分⽀复制到新建函数中，并对它进⾏ 适当调整。
> 为了顺利进⾏这⼀步驟，你可能需要将超类中的某些private宇段声明为protected.

-[ ] 编译，测试。
-[ ] 在超类中删掉条件表达式内被复制了的分⽀。 
-[ ] 编译，测试。
-[ ] 针对条件表达式的每个分⽀，重复上述过程，直到所有分⽀都被移到⼦类内的函数为⽌。
-[ ] 将超类之中容纳条件表达式的函数声明为抽象函数。

## 范例

请允许我继续使⽤“员⼯与薪资”这个简单⽽⼜乏味的例⼦。
我的类是从 Replace Type Code with State/Strategy（227）那个例⼦中拿来的，
因此，示意图就如图9-1所示（如果想知道这个图是怎么得到的，请看第8章的范例）。

```plantuml
EmployeeType <|-- Engineer
EmployeeType <|-- Salesman
EmployeeType <|-- Manager

Employee -right-> EmployeeType
```

[Replace Type Code with State(Strategy)](..%2F..%2Fchapter08%2Fsection15%2FReplace%20Type%20Code%20with%20State%28Strategy%29.md)

1) switch语句已经被很好地提炼出来，因此我不必费劲再做⼀遍。
   不过我需要将 它移到 EmployeeType类，因为 EmployeeType类 才是要被继承的类。
```java
class EmployeeType {
    int payAmount(Employee emp) {
        return switch (emp.getType()) {
            case Employee.ENGINEER -> emp.get_monthlySalary();
            case Employee.SALESMAN -> emp.get_monthlySalary() + emp.get_commission();
            case Employee.MANAGER -> emp.get_monthlySalary() + emp.get_bonus();
            default -> throw new RuntimeException("Incorrect Employee");
        };
    }
}
```

2) 由于我需要Employee的数据，所以，需要将 Employee对象 作为参数，传递给 payAmount()。 
   这些数据中的⼀部分，也许可以移到 EmployeeType类 来，但那是另⼀项重构要关⼼的问题了。

调整代码，使之通过编译，然后我修改 Employee 中的 payAmount() 函数，令它委托 EmployeeType：
```java
class Employee {
    int payAmount() {
        return _type.payAmount(this);
    }
}
```

3) 现在，我可以处理switch语句了。  
   这个过程有点像淘⽓⼩男孩折磨⼀只昆⾍⼀每次掰掉它⼀条腿。
   ⾸先，我把 switch语句中的 Engineer分⽀，复制到 Engineer类：
   接下来，我重复上述过程，直到所有分⽀都被去除为⽌：

4) 最后，我将 payAmount() 声明为抽象函数：
```java
class EmployeeType {
    abstract int payAmount(Employee emp);
}
```

