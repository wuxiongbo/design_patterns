# Encapsulate Downcast（封装向下转型）
某个函数返回的对象，需要由函数调⽤者执⾏向下转型 （downcast）。 


将向下转型动作移到函数中。
```java
Object lastReading(){
    return readings.lastElement();
}
```

```java
Reading lastReading(){
    return (Reading) readings.lastElement();
}
```


## 动机

在强类型 OO语⾔中，向下转型是最烦⼈的事情之⼀。
之所以很烦⼈，是因为从感觉上来说，它完全没有必要：
你竟然越俎代庖地告诉编译器某些应该由编译器⾃⼰计算出来的东西。
但是，由于计算对象类型往往⽐较⿇烦，你还是常常需要亲⾃告 诉编译器对象的确切类型。
向下转型在Java特别盛⾏，因为Java没有模板机制，因此如果你想从集合之中取出⼀个对象，就必须进⾏向下转型。

（
自从Java5加⼊模板机制以后，⾮向下转型不可的场合⼏乎绝迹。
读者如果发现⾃⼰写出需要向下转型的代码，在考虑使⽤本重构⼿法之前，应该⾸先考虑，是否可以 代之以模板类。
——译者注
）

向下转型也许是⼀种⽆法避免的罪恶，但你仍然应该尽可能少做。
如果你的某个函数返回⼀个值，并且你知道所返回的对象类型⽐函数签名所昭告的更特化，你便是在函数⽤户⾝上强加了⾮必要的⼯作。
这种情况下你不应该要求⽤户承担向下转型的责任，应该尽量为他们提供准确的类型。

以上所说的情况，常会在返回迭代器或集合的函数⾝上发⽣。
此时，你就应该观察⼈们拿这个选代器⼲什么⽤，然后针对性地提供专⽤函数。


## 做法

-[ ] 找出必须对函数调⽤结果进⾏向下转型的地⽅。
>这种情况通常出现在返回⼀个集合或选代器的函数中.

-[ ] 将向下转型动作搬移到该函数中。 
>针对返回集合的函数，使⽤ Encapsulate Collection（208）

## 范例

下⾯的例⼦中，我以 Reading 表⽰ “书籍”。
我还拥有⼀个名为 lastReading() 的函数，它从⼀个⽤于保存 Reading对象 的vector中，返回其最后⼀个元素：
```java
class Site{
    private Vector readings;
    Object lastReading(){
        return readings.lastElement();
    }
}
```

我应该将这个函数变成：
```java
class Site{
    private Vector readings;
    Reading lastReading(){
        return (Reading) readings.lastElement();
    }
}
```

当我拥有⼀个集合时，上述那么做，就很有意义。

如果 “保存Reading对象”的 集合，被放在 Site类 中，并且，我看到了如下的客户端代码：
```java
public void client(Site thesite) {
    Reading lastReading = (Reading) thesite.readings().lastElement();
}
```

我就可以不再把向下转型的⼯作推给⽤户，并得以向⽤户隐藏集合：
```java
public void client(Site thesite) {
    Reading lastReading = thesite.lastReading();
}
```

如果，你修改函数，将其 返回类型 改为 原返回类型的⼦类，
那，就是改变了函数签名，但并不会破坏客户端代码，
因为，编译器知道它总是可以将⼀个⼦类⾃动 向上转型 为 超类。
当然，你必须确保这个⼦类，不会破坏 超类 带来的任何契约。

