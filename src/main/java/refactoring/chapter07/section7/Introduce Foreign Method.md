# 引入外加函数 Introduce Foreign Method

你需要为提供服务的类增加一个函数，但你无法修改这个类。


在客户类中建立一个函数，并以第一参数形式传入一个服务类实例。
```java
class Report {
  // ...
  void sendReport() {
    Date nextDay = new Date(
            previousEnd.getYear(), 
            previousEnd.getMonth(), 
            previousEnd.getDate() + 1);
    // ...
  }
}
```

```java
class Report {
    
  // ...
  void sendReport() {
    Date newStart = nextDay(previousEnd);
    // ...
  }
  
  private static Date nextDay(Date arg) {
    return new Date(
            arg.getYear(), 
            arg.getMonth(), 
            arg.getDate() + 1);
  }
}
```


## 动机
这种事情发生过太多次了：你正在使用一个类，它真的很好，为你提供了需要的所有服务。
而后，你又需要一项新服务，这个类却无法供应。
于是你开始咒骂： 不能，你就得在客户端编码，补足你要的那个函数。

如果客户类只使用这项功能一次，那么，额外编码工作没什么大不了，甚至可能根本不需要原本提供服务的那个类。
然而，如果你需要多次使用这个函数，就得不 断重复这些代码。
还记得吗，重复代码是软件万恶之源。
这些重复代码应该被抽出一个明确信号：这个函数原本应该在提供服务的类中实现。

如果你发现自己为一个服务类建立了大量外加函数，或者发现有许多类都需要同样的外加函数，
就不应该再使用本项重构，而应该使用 Introduce Local Extension（164）。

但是不要忘记：外加函数终归是权宜之计。
如果有可能，你仍然应该将这些函数搬移到它们的理想家园。
如果由于代码所有权的原因使你无法做这样的搬移，就把外加函数交给服务类的拥有者，请他帮你在服务类中实现这个函数。


## 做法
-[ ]  在客户类中建立一个函数，用来提供你需要的功能。
一这个函数不应该调用客户类的任何特性。如果它需要一个值，把该值当作参数传给它．
-[ ]  以服务类实例作为该函数的第一个参数。
-[ ]  将该函数注释为：“ **外加函数（foreign method）**，应在服务类实现。”
   >•这么一来，如果将来有机会将 ‘外加函数’ 搬移到 ‘服务类’ 中时，你便可以轻松找出这些外加函数。

 
程序中，我需要跨过一个收费周期。原本代码像这样：
```java
class Report {
  // ...
  void sendReport() {
    Date nextDay = new Date(
            previousEnd.getYear(), 
            previousEnd.getMonth(), 
            previousEnd.getDate() + 1);
    // ...
  }
}
```

我可以将，赋值运算右侧代码，提炼到一个独立函数中。
这个函数，就是Date类的一个外加函数：
```java
class Report {
    
  // ...
  void sendReport() {
    Date newStart = nextDay(previousEnd);
    // ...
  }
  
  private static Date nextDay(Date arg) {
    return new Date(
            arg.getYear(), 
            arg.getMonth(), 
            arg.getDate() + 1);
  }
}
```