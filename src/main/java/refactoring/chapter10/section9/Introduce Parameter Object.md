# Introduce Parameter Object（引⼊参数对象）

某些参数总是很⾃然地同时出现。 

**以⼀个对象取代这些参数。**

```puml
class Customer{
  amountInvoicedIn (start: Date, end: Date)
  amountReceivedIn (start: Date, end: Date)
  amountOverdueIn (start: Date, end: Date)
}

class Customer{
  amountInvoicedIn (DateRange)
  amountReceivedIn (DateRange)
  amountOverdueIn (DateRange)
}
```

## 动机

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
你常会看到特定的⼀组参数总是⼀起被传递。
可能有好⼏个函数都使⽤这⼀组 参数，这些函数可能⾪属同⼀个类，也可能⾪属不同的类。
这样⼀组参数就是所谓的Data Clumps（数据泥团），我们可以运⽤⼀个对象包装所有这些数据，再以该对 象取代它们。
哪怕只是为了把这些数据组织在⼀起，这样做也是值得的。
本项重构 的价值在于缩短参数列，⽽你知道，过长的参数列总是难以理解的。
此外，新对象 所定义的访问函数还可以使代码更具⼀致性，这又进⼀步降低了理解和修改代码的难度。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
本项重构还可以带给你更多好处。当你把这些参数组织到⼀起之后，往往很快可以发现⼀些可被移⾄新建类的⾏为。
通常，原本使⽤那些参数的函数对这⼀组参数会有⼀些共通的处理，如果将这些共通⾏为移到新对象中，你可以减少很多重复代码。

## 做法

-[ ] 新建⼀个类，⽤以表现你想替换的⼀组参数。将这个类设为不可变的。 
-[ ] 编译。
-[ ] 针对使⽤该组参数的所有函数，实施 Add Parameter(275)，传⼊上述新建类的实例对象，并将此参数值设为null.
> 如果你所修改的函数被其他很多函数调⽤，那么可以保留修改前的旧函数，并今它调⽤修改后的新函数。
你可以先对旧函数进⾏重构，然后逐⼀修改调⽤端使其调⽤新函数，最后再将旧函数删除。
-[ ] 对于Data Clumps中的每⼀项（在此均为参数），从函数签名中移除之，并修改调⽤端和函数本体，令它们都改⽽通过新的参数对象取得该值。 
-[ ] 每去除⼀个参数，编译并测试。
-[ ] 将原先的参数全部去除之后，观察有⽆适当函数可以运⽤Move Method （142）搬移到参数对象之中。 
> 被搬移的可能是整个函数，也可能是函數中的⼀个段落。
如果是后者，⾸先使⽤Extract Method（I10）将该段落提炼为⼀个独⽴函教，再搬移这⼀新建函数。


## 范例

下⾯是⼀个“账⽬和账项”范例。
表示“账项”的Entry实际上只是个简单的数据容器：

```java
class Entry {
  private Date _chargeDate;
  private double _value;
  Entry(double value, Date chargeDate) {
    _value = value;
    _chargeDate = chargeDate;
  }
  Date getDate() {
    return _chargeDate;
  }
  double getValue() {
    return _value;
  }
}

```

我关注的焦点是⽤以表示“账⽬”的Account，它保存了⼀组Entry对象，并有⼀个函数⽤来计算两个⽇期间的账项总量：
```java
class Account {
  double getFlowBetween(Date start, Date end) {
    double result = 0;
    Enumeration e = _entries.elements();
    while (e.hasMoreElements()) {
      Entry each = (Entry) e.nextElement();
      if (each.getDate().equals(start) || each.getDate().equals(end) ||
          (each.getDate().after(start) && each.getDate().before(end))) {
        result += each.getValue();
      }
    }
    return result;
  }
  private Vector _entries = new Vector();
}
```
```java
class Client {
    void clientCode() {
        double flow = anAccount.getFlowBetween(startDate, endDate);
    }
}

```
我已经记不清有多少次看⻅代码⽤⼀对值来表示⼀个范围，
例如,表示⽇期范围 的start和end、表示数值范围的upper和lower，等等。
我知道为什么会发⽣这种情况，毕竟我⾃⼰也经常这样做。
不过，⾃从学到 Range模式［Fowler，AP］之后，我就尽量以“范围对象”取⽽代之。
我的第⼀个步骤是声明⼀个简单的数据容器，⽤以表示范围：
```java
class DateRange {
  DateRange(Date start, Date end) {
    _start = start;
    _end = end;
  }
  Date getStart() {
    return _start;
  }
  Date getEnd() {
    return _end;
  }
  private final Date _start;
  private final Date _end;
}

```

我把 DateRange 设为不可变，也就是说，其中所有字段都是final，只能由构造函数来赋值，因此没有任何函数可以修改其中任何字段值。
这是⼀个明智的决定，因为这样可以避免别名带来的困扰。
Java的函数参数都是按值传递的，不可变类正好能够模仿Java参数的⼯作⽅式，因此这种做法对于本项重构是最合适的。

接下来我把 DateRange对象 加到 getFlowBetween()函数的参数列中：
⾄此，只需编译⼀下就⾏了，因为我尚未修改程序的任何⾏为。
下⼀个步骤是去除旧参数之⼀，以新建对象取⽽代之。
⾸先，我删除start参数， 并修改getF1owBetween（）函数及其调⽤者，让它们转⽽使⽤新对象：
然后，我将end参数也移除：
```java
class Account {
  double getFlowBetween(DateRange range) {
    double result = 0;
    Enumeration e = _entries.elements();
    while (e.hasMoreElements()) {
      Entry each = (Entry) e.nextElement();
      if (each.getDate().equals(range.getStart()) ||
          each.getDate().equals(range.getEnd()) ||
          (each.getDate().after(range.getStart()) &&
              each.getDate().before(range.getEnd()))) {
        result += each.getValue();
      }
    }
    return result;
  }
  private Vector _entries = new Vector();
}
```

现在，我已经引⼊了参数对象。
我还可以将适当的⾏为从其他函数移到这个新建对象中，进⼀步从本项重构获得更⼤利益。
这⾥，我选定条件表达式中的代码， 实施 Extract Method （110）和 Move Method （142），
最后得到如下代码： 
```java
class Account {
  double getFlowBetween(DateRange range) {
    double result = 0;
    Enumeration e = _entries.elements();
    while (e.hasMoreElements()) {
      Entry each = (Entry) e.nextElement();
      if (range.includes(each.getDate())) {
        result += each.getValue();
      }
    }
    return result;
  }
  private Vector _entries = new Vector();
}

class Range {
  boolean includes(Date arg) {
    return (arg.equals(_start) ||
        arg.equals(_end) ||
        (arg.after(_start) && arg.before(_end)));
  }
  private final Date _start;
  private final Date _end;
}

```

如此单纯的提炼和搬移动作，我通常⼀步完成。
如果在这个过程中出错，我可以回到重构前的状态，然后分成两个较⼩步骤重新进⾏。
