# Extract Interface（提炼接⼝）

若⼲客户使⽤类接⼝中的同⼀⼦集，或者两个类的接⼝有部分相同。


将相同的⼦集提炼到⼀个独⽴接⼝中。


```puml
class Employee{
    getRate
    hasSpecialSkill
    getName
    getDepartment
}

```
```puml
interface Billable{
    getRate
    hasSpecialSkill
}

class Employee implements Billable{
    getRate
    hasSpecialSkill
    getName
    getDepartment
}
```

## 动机

类之间，彼此互⽤的⽅式有若⼲种。
“使⽤⼀个类“通常意味⽤到该类的 所有责任区。
另⼀种情况是，某⼀组客户只使⽤ 类责任区 中的 ⼀个特定⼦集。
再⼀种情况则是，这个类需要与 所有协助处理某些特定请求的类 合作。

对于后两种情况，将真正⽤到的这部分责任分离出来通常很有意义，
因为，这样可以使系统的⽤法更清晰，同时，也更容易看清系统的责任划分。
如果新的类需要⽀持上述⼦集，也⽐较能够看清⼦集内有些什么东⻄。

在许多⾯向对象语⾔中，这种责任划分是通过 多继承（multiple inheritance）来实现的。
你可以针对每组⾏为建⽴⼀个类，再将它们组合于同⼀个实现中。
Java只提供 单继承（single inheritance），但你可以运⽤ 接⼝（interface）来昭示并实现上述需求。
接⼝，对于Java程序的设计⽅式有着巨⼤的影响，就连 Smalltalk程序员 都认为 接⼝是⼀⼤进步！

Extract Superclass （336）和 Extract Interface（341）之间有些相似之处。
Extract Interface（341）只能提炼共通接⼝，不能提炼共通代码。
使⽤ Extract Interface（341）可能造成难闻的“重复”坏味道，
幸⽽你可以运⽤ Extract Class（149）先把共通⾏为放进⼀个组件中，
然后，将⼯作委托该组件，从⽽解决这个问题。
如果有不少共通⾏为，Extract Superclass（336）会⽐较简单，但是每个类只能有⼀个超类。

如果某个类在不同环境下扮演截然不同的⻆⾊，使⽤接⼝就是个好主意。
你可以针对每个⻆⾊以 Extract Interface（341）提炼出相应接⼝。

另⼀种可以⽤上 Extract Interface（341）的情况是：
你想要描述⼀个类的外部依赖接⼝（outbound interface，即这个类要求服务提供⽅提供的操作）。
如果你打算将来加⼊其他种类的服务对象，只需要求它们实现这个接⼝即可。

## 做法

-[ ] 新建⼀个空接⼝。 
-[ ] 在接⼝中声明待提炼类的共通操作。 
-[ ] 让相关的类实现上述接⼝。 
-[ ] 调整客户端的类型声明，令其使⽤该接⼝。

## 范例

Timesheet类 表示员⼯为客户⼯作的时间表，从中可以计算客户应该⽀付的费⽤。
为了计算这笔费⽤，Timesheet 需要知道员⼯级别，以及该员⼯是否有特殊技能：
```
double charge (Employee emp, int days)｛
    int base = emp.getRate() * days;
    if (emp.hasSpecialSkill())
        return base *1.05;
    else
        return base;
｝
```

除了提供员⼯的级别和特殊技能信息外，Employee还有很多其他⽅⾯的功能，但本应⽤程序只需这两项功能。
我可以针对这两项功能定义⼀个接⼝，从⽽强调 “我只需要这部分功能” 的事实：
```
interface Billable ｛
    public int getRate();
    public boolean hasSpecialSkill(); 
...
```

然后，我声明让Employee实现这个接⼝：
```
class Employee implements Billable ...
```


完成以后，我可以修改charge()函数声明，强调该函数只使⽤ Employee的这部分⾏为：
```
double charge (Billable emp, int days） ｛ 
  int base = emp.get.Rate() * days;
  iE （emp.hasSpecialSkill（））
     return base * 1.05;
  else 
     return base;
}
```

到⽬前为⽌，我们只不过是在⽂档化⽅⾯有⼀点收获。
单就这⼀个函数⽽⾔，这样的收获并没有太⼤价值；
但如果有若⼲个类都使⽤ Billable 接⼝，它就会很有⽤。

如果我还想计算电脑租⾦，巨⼤的收获就显露出来了：
要想计算客户租⽤电脑的费⽤，我只需让 computer类 实现 Billable 接⼝，
然后，就可以把租⽤电脑的时间也填到时间表上了。

