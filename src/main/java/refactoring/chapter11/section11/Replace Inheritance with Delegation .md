# Replace Inheritance with Delegation （以委托取代继承）

某个⼦类只使⽤超类接⼝中的⼀部分，或是根本不需要继承⽽来的数据。

在⼦类中新建⼀个字段⽤以保存超类；
调整⼦类函数，令它改⽽委托超类；
然后去掉两者之间的继承关系。

```plantuml
class Vector{
isEmpty()
}

class Stack extends Vector{}
```

```plantuml
class Vector{
isEmpty()
}

class Stack {
isEmpty()
}

Stack -right->"1" Vector

note left of Stack::isEmpty
  return _vector.isEmpty();
end note
```

## 动机

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
继承是个好东⻄，但有时候它并不是你要的。
你常常会遇到这样的情况：⼀开始继承了⼀个类，随后发现超类中的许多操作并不真正适⽤于⼦类。
这种情况下，你所拥有的接⼝并未真正反映出⼦类的功能。
或者，你可能发现你从超类中继承了⼀⼤堆⼦类并不需要的数据，抑或你可能发现超类中的某些protected函数对⼦类并没有什么意义。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
你可以选择容忍，并接受传统说法：⼦类可以只使⽤超类功能的⼀部分。
但这样做的结果是：代码传达的信息与你的意图南辕北辙——这是⼀种混淆，你应该将它去除。

&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
如果以委托取代继承，你可以更清楚地表明：你只需要受托类的⼀部分功能。
接⼝中的哪⼀部分应该被使⽤，哪⼀部分应该被忽略，完全由你主导控制。
这样做的成本则是需要额外写出委托函数，但这些函数都⾮常简单，极少可能出错。

## 做法

⼝ 在⼦类中新建⼀个字段，使其引⽤超类的⼀个实例，并将它初始化为 this。
⼝ 修改⼦类内的所有函数，让它们不再使⽤超类，转⽽使⽤上述那个受托字段。 每次修改后，编译并测试。
•你不能这样修改⼦类中通过super调⽤超类函数的代码，否則它们会陷⼊⽆限递归。这种函數只有在继承关系被打破后才能修改。
⼝ 去除两个类之间的继承关系，新建⼀个受托类的对象赋给受托字段。
⼝ 针对客户端所⽤的每⼀个超类函数，为它添加⼀个简单的委托函数。
⼝ 编译，测试。

## 范例

滥⽤继承的⼀个经典范例就是让 stack类 继承 vector类——Java 1.1的⼯具库（java.util）恰好就是这样做的。
（这些淘⽓的孩⼦啊！）不过，作为范例，我只给出⼀个⽐较简单的形式：

```java
class MyStack extends Vector {
    public void push(Object element) {
        insertElementAt(element, 0);
    }

    public Object pop() {
        Object result = firstElement();
        removeElementAt(0);
        return result;
    }
}
```

只要看看 MyStack 的⽤户，我就会发现，⽤户只要它做4件事：push（）、pop（）、 size（）和isEmpty（）。
后两个函数是从vector继承来的。

我要把这⾥的继承关系改为委托关系。⾸先，我要在 MyStack 中新建⼀个字段，⽤以保存受托的vector对象。
⼀开始我把这个字段初始化为this，这样在重构进⾏过程中，我就可以同时使⽤继承和委托：

```
private Vector _vector = this;
```

现在，我开始修改 MyStack的函数，让它们使⽤委托关系。
⾸先从push（）开始：

```java
public void push(Object element) {
    _vector.insertElementAt(element, 0);
}
```

此时我可以编译并测试，⼀切都将运转如常。

现在轮到pop（）：

```java
public Object pop() {
    Object result = _vector.firstElement();
    _vector.removeElementAt(0);
    return result;
}

```

修改完所有⼦类函数后，我可以打破与超类之间的联系了，

class MyStack ~~extends Vector~~
private Vector _vector = new Vector();

然后，对于stack客户端可能⽤到的每⼀个Vector函数，我都必须在MyStack 中添加⼀个简单的委托函数：

```java
public int size() {
    return _vector.size();
}

public boolean isEmpty() {
    return _vector.isEmpty();
}
```

现在我可以编译并测试。

如果我忘记加⼊某个委托函数，编译器会告诉我。