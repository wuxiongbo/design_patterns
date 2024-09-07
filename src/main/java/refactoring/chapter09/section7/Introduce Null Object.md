# Introduce Null Object （引⼊ Null 对象）

你需要再三检查，某对象 是否为 null。

将 ‘null值’  替换为  ‘null对象’ 。

## 动机

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
多态的最根本好处在于：你不必再向对象询问 “你是什么类型”⽽后根据得到的答案调⽤对象的某个⾏为—你只管调⽤该⾏为就是了，其他的⼀切多态机制会为你安排妥当。
当某个字段内容是null时，多态可扮演另⼀个较不直观（亦较不为⼈所知）的⽤途。
让我们先听听 Ron Jeffries 的故事。

---
Ron Jeffries—

我们第⼀次使⽤ Null Object模式，是因为 Rich Garzaniti 发现，系统在向对象发送⼀个消息之前，总要检查对象是否存在，这样的检查出现很多次。
我们可能 会向⼀个对象索求它所相关的Person对象，然后再问那个对象是否为null。
如果对象的确存在，我们才能调⽤它的 rate（）函数 以查询这个⼈的薪资级别。
我们在好些地⽅都是这样做的，造成的重复代码让我们很烦⼼。 
所以，我们编写了⼀个 MissingPerson类，让它返回 ‘0’薪资等级
［我们也把空对象（null object）称为虛拟对象（missing object）］。 
很快地，MissingPerson 就有了很多函数，rate（）⾃然是其中之⼀。
如今，我们的系统有超过80个空对象类。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
我们常常在显示信息的时候使⽤空对象。例如，我们想要显示⼀个 Person对象信息，它⼤约有20个实例变量。
如果这些变量可被设为null，那么打印⼀个Person对象的⼯作将⾮常复杂。
所以，我们不让实例变量被设为null，⽽是插⼊各式各样的空对象——它们都知道如何正确地显示⾃⼰。
这样，我们就可以摆脱⼤量过程化的代码。 

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
我们对空对象的最聪明运⽤，就是拿它来表示不存在的 Gemstone会话：  
我们使⽤Gemstone数据库来保存成品（程序代码），但我们更愿意在没有数据库的情况下进⾏开发，每过⼀周左右再把新代码放进Gemstone数据库。
然⽽在代码的某些地⽅，我们必须登录⼀个Gemstone会话。
当没有Gemstone数据库时，我们就仅仅安插⼀个“虚构的Gemstone会话”，其接⼝和真正的Gemstone会话⼀模⼀样， 使我们⽆需判断数据库是否存在，就可以进⾏开发和测试。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
空对象的另⼀个⽤途是表现出 “虚构的箱仓”（missing bin）。
所谓 “箱仓”，这⾥是指集合，⽤来保存某些薪资值，并常常需要对各个薪资值进⾏ 加和 或 遍历. 
如果某个箱仓不存在，我们就给出⼀个虚构的箱仓对象，其⾏为和⼀个空箱仓⼀样。
这个虚构箱仓知道⾃⼰其实不带任何数据，总值为O.
通过这种做法，我们就不必为上千位员⼯每⼈产⽣数⼗来个空箱对象了。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
使⽤空对象时有个⾮常有趣的性质：
系统⼏乎从来不会因为空对象⽽被破坏。
由于空对象对所有外界请求的响应都和真实对象⼀样，所以，系统⾏为总是正常的。
但这并⾮总是好事，有时会造成问题的侦测和查找上的困难，因为从来没有任何东⻄被破坏。
当然，只要认真检查⼀下，你就会发现空对象有时出现在不该出现的地⽅。

请记住：空对象⼀定是常量，它们的任何成分都不会发⽣变化。

因此我们可以使⽤Singleton模式［Gang of Four］来实现它们，
例如不管任何时候，只要你索求⼀个MissingPerson对象，得到的⼀定是MissingPerson 的唯⼀实例.
---
关于Null Object模式，你可以在Woolf［Woolf]中 找到更详细的介绍。


## 做法

-[ ] 为源类建⽴⼀个⼦类，使其⾏为就像是源类的null版本。
  在源类和null了类中都加上 isNull() 函数，前者的 isNull() 应该返回false，
  后者的 isNull() 应该返回true。
>下⾯这个办法也可能对你有所帮助：建⽴⼀个 nullable接⼝，将 isNull()函数放在其中，让源类实现这个接⼝。

>另外，你也可以创建⼀个测试接⼝，专⻔⽤来检查对象是否为null。

-[ ] 编译。
-[ ] 找出所有“索求源对象却获得⼀个null”的地⽅。修改这些地⽅，使它们改⽽获得⼀个空对象。
-[ ] 找出所有“将源对象与null做⽐较”的地⽅。修改这些地⽅，使它们调⽤ isNull() 函数。
>你可以每次只处理⼀个源对象及其客户程序，编译并测试后，再处理另⼀个源对象.

>你可以在“不该再出现null”的地⽅放上⼀些断⾔，确保null的确不再出现。这可能对你有所帮助.

-[ ] 编译，测试。 
-[ ] 找出这样的程序点：如果对象不是 null，做A动作，否则做B动作。 
-[ ] 对于每⼀个上述地点，在null类中覆写A动作，使其⾏为和B动作相同。 
-[ ] 使⽤上述被覆写的动作，然后删除“对象是否等于null”的条件测试。编译并测试。


## 范例

⼀家公⽤事业公司的系统以site表示地点（场所）。
庭院宅第（house）和 集体公寓（apartment）都使⽤该公司的服务。
任何时候每个地点都拥有（或说都对应于）⼀个顾客，顾客信息以Customer表示：
```java
class Site{
    Customer getCustomer(){
        return _customer;
    }
    Customer _customer;
}
```
Customer有很多特性，我们只看其中三项：
```java
class Customer{
    String getName();
    BillingPlan getPlan();
    PaymentHistory getHistory();
}
```

本系统⼜以 PaymentHistory类，表示顾客的付款记录，它也有其⾃⼰的特性：
```java
class PaymentHistory{
    int getWeeksDelinquentInLastYear();
}
```

上⾯的各种取值函数允许客户取得各种数据。
但有时候⼀个地点的顾客搬⾛了，新顾客还没搬进来，此时这个地点就没有顾客。
由于这种情况有可能发⽣，所以我们必须保证 customer的所有⽤户 都能够处理 “customer对象等于null” 的情况。 
下⾯是⼀些⽰例⽚段：

```java
public void function1(){
    Customer customer = site.getCustomer();
    
    
    //...
    BillingPlan plan;
    if(customer == null) {
        plan = BillingPlan.basic();
    } else {
        plan = customer.getPlan();
    }
    
    // ...
    String customerName;
    if(customer == null) {
        customerName = "occupant";
    } else {
        customerName = customer.getName();
    }
    
    // ...
    int weeksDelinquent;
    if(customer == null) {
        weeksDelinquent = 0;
    } else {
        weeksDelinquent = customer.getHistory().getWeeksDelinquentInLastYear();
    }
    
}
```

这个系统中可能有许多地⽅使⽤ site和 customer对象，它们都必须检查 customer对象是否等于null，⽽这样的检查完全是重复的。
看来是使⽤空对象的时候了。 

⾸先新建⼀个NullCustomer，并修改Customer，使其⽀持“对象是否为null” 的检查：
```java

class NullCustomer extends Customer {
    boolean isNull(){
        return true;
    }
}

class Customer{
    boolean isNull(){
        return false;
    }
}

```

如果你⽆法修改 customer，可以使⽤第266⻚的做法：建⽴⼀个新的测试接⼝。 

如果你喜欢，也可以新建⼀个接⼝，昭告⼤家“这⾥使⽤了空对象”；
```java
interface Null{
    boolean isNull();
}
```

```java
class Customer implements Nullable{
    boolean isNull(){
        return ture;
    }
}
```

我还喜欢加⼊⼀个⼯⼚函数，专⻔⽤来创建 NullCustomer对象。
这样⼀来，⽤户就不必知道空对象的存在了：
```java
class Customer{
    static Customer newNull(){
        return new NullCustomer();
    }
}

```

接下来的部分稍微有点麻烦。
对于所有“返回null”的地⽅，我都要将它改为 “返回空对象”。
此外，我还要把 foo==null 这样的检查，替换成 foo.isNull()。
我发现下列办法很有⽤：
查找所有提供 **Customer对象** 的地⽅，将它们都加以修改，
使它们不能返回 null，改⽽返回⼀个 NullCustomer对象。

```java
class Site{
    Customer getCustomer(){
        return (_customer == null) ? Customer.newNull() : _customer;
    }
    Customer _customer;
}
```


另外，我还要修改所有使⽤customer对象的地⽅，让它们以 isNull()函数 进⾏检查，
不再使⽤ ==null 检查⽅式。

```java
public void function1(){
    Customer customer = site.getCustomer();
    
    //...
    BillingPlan plan;
    if(customer.isNull()) {
        plan = BillingPlan.basic();
    } else {
        plan = customer.getPlan();
    }
    
    // ...
    String customerName;
    if(customer.isNull()) {
        customerName = "occupant";
    } else {
        customerName = customer.getName();
    }
    
    // ...
    int weeksDelinquent;
    if(customer.isNull()) {
        weeksDelinquent = 0;
    } else {
        weeksDelinquent = customer.getHistory().getWeeksDelinquentInLastYear();
    }
    
}

```

毫⽆疑问，这是本项重构中最需要技巧的部分。
对于每⼀个需要替换的可能等于 null 的对象，我都必须找到所有检查它是否等于null的地⽅，并逐⼀替换。
如果这个对象被传播到很多地⽅，追踪起来就很困难。
上述范例中，我必须找出每⼀个类型为customer的变量，以及它们被使⽤的地点。
很难将这个过程分成更⼩的步骤。
有时候我发现可能等于null的对象只在某⼉处被⽤到，那么替换⼯作⽐较简单：
但是⼤多数时候我必须做⼤量替换⼯作。
还好，撤销这些替换并不困难，因为我可以不太困难地找出对 isNull()的调⽤动作，
但这毕竟也是很零乱很恼⼈的。

这个步骤完成之后，如果编译和测试都顺利通过，我就可以宽⼼地露出笑容了。 
接下来的动作⽐较有趣。
到⽬前为⽌，使⽤ isNull()函数 尚未带来任何好处。
只有把相关⾏为移到 NullCustomer中，并去除条件表达式之后，我才能得到切实的利益。
我可以逐⼀将各种⾏为移过去。

⾸先，从“取得顾客名称”这个函数开始。
此时的客户端代码⼤约如下：
```java
public void function1() {
  String customerName;
  if (customer.isNull()) {
    customerName = "occupant";
  } else {
    customerName = customer.getName();
  }

}
```

⾸先，为 NullCustomer 加⼊⼀个合适的函数，通过这个函数来取得顾客名称：
```java
class NullCustomer extends Customer {
  String getName() {
    return "occupant";
  }
}

```

现在，我可以去掉条件代码了：

```java
public void function1() {
  String customerName = customer.getName();
}

```

接下来我以相同⼿法处理其他函数，使它们对相应查询做出合适的响应。
此外，我还可以对修改函数做适当的处理。

于是下⾯这样的客户端程序：
```java
public void function1() {
  if (!customer.isNull()) {
    customer.setPlan(BillingPlan.special());
  }
}
```
就变成了这样：
```java
public void function1() {
  customer.setPlan(BillingPlan.special());
}
```
```java
class NullCustomer extends Customer {
    void setPlan(BillingPlan arg) {
    }
}
```

请记住：只有当⼤多数 客户代码 都要求 空对象 做出相同响应时，这样的⾏为搬移才有意义。
注意，我说的是“⼤多数”⽽不是“所有”。
任何⽤户如果需要 空对象 做出不同响应，他们仍然可以使⽤ isNull()函数来测试。
只要⼤多数客户端都要求空对象做出相同响应，他们就可以调⽤默认的 null⾏为，⽽你也就受益匪浅了。 
上述范例略带差异的某种情况是，某些客户端使⽤ Customer函数 的运算结果：
```java
public void function1() {
    int weeksDelinquent;
    if (customer.isNull()) {
        weeksDelinquent = 0;
    } else {
        weeksDelinquent = customer.getHistory().getWeeksDelinquentInLastYear();
    }
}
```

我可以新建⼀个NullPaymentHistory类，⽤以处理这种情况：
```java
class NullPaymentHistory extends PaymentHistory {
    int getWeeksDelinquentInLastYear() {
        return 0;
    }
}
```

并修改NullCustomer，让它返回⼀个NullPaymentHistory对象：
```java
class NullCustomer extends Customer {
   PaymentHistory getHistory() {
     return PaymentHistory.newNull();
   }
}
```

然后，我同样可以删除条件，简写为一行代码：
`int weeksDelinquent = customer.getHistory().getWeeksDelinquentInLastYear();`

```java
public void function1() {
   int weeksDelinquent = customer.getHistory().getWeeksDelinquentInLastYear();
}
```
你常常可以看到这样的情况：空对象 会返回 其他空对象。


## 范例：测试接⼝

除了定义 isNull()之外，你也可以建⽴⼀个⽤以检查 “对象是否为null” 的接⼝。 

使⽤这种办法，需要新建⼀个Null接⼝，其中不定义任何函数：
```java
interface Null{}
```
然后，让空对象实现Null接⼝：
```java
class NullCustomer extends Customer implements Null{
    //...
}
```
然后，我就可以⽤ instanceof 操作符，检查对象是否为null：
`aCustomer instanceof Null`

通常，我尽量避免使⽤ instanceof操作符，但在这种情况下，使⽤它是没问题的。
⽽且这种做法还有另⼀个好处： 不需要修改Customer。
这么⼀来，即使⽆法修改Customer源码，我也可以使⽤空对象。


## 其他特殊情况
使⽤本项重构时，你可以有⼏种不同的空对象，
例如你可以说 
“没有顾客”（新建的房⼦和暂时没⼈住的房⼦）和 
“不知名顾客”（有⼈住，但我们不知道是谁）这 两种情况是不同的。
果真如此，你可以针对不同的情况建⽴不同的空对象类。
有时候，空对象也可以携带数据，例如 不知名顾客的使⽤记录等，于是我们可以在查出顾客姓名之后将账单寄给他。

本质上⽶说，这是⼀个⽐ NullObject模式 更⼤的模式：Special Case模式。
所谓 特例类（special case），也就是某个类的特殊情况，有着特殊的⾏为。
因此，表示“不知名顾客”的UnknownCustomer 和 表示“没有顾客”的NoCustomer 都是Customer 的特例。
你经常可以在表示数量的类中看到这样的“特例类”，例如 Java浮点数有“正⽆穷⼤”、“负⽆穷⼤” 和 “⾮数量”（NaN）等特例。
特例类的价值是：它们可以降低你的“错误处理”开销，例如 浮点运算决不会抛出异常。
如果你对NaN做浮点运算，结果也会是个NaN。
这和 “空对象 的 访问函数 通常返回 另⼀个空对象” 是⼀样的道理。