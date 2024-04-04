移除对参数的赋值

## 动机
⾸先，我要确定⼤家都清楚 “对参数赋值” 这个说法的意思。
如果你把⼀个名为foo的对象作为参数传给某个函数，那么 “对参数赋值”意味改变foo，使它引⽤另⼀个对象。
如果你在“被传⼊对象”⾝上进⾏什么操作，那没问题，我也总是这样⼲。

我只针对 “foo被改⽽指向另⼀个对象” 这种情况来讨论：

```java
void aMethod (Object foo)｛
    foo.modifyInSomeWay();  // that's OK
    foo = anotherobject;    // trouble and despair will follow you
```

我之所以不喜欢这样的做法，因为它降低了代码的清晰度，⽽且混⽤了按值传递和按引⽤传递这两种参数传递⽅式。
Java只采⽤按值传递⽅式（稍后讨论），我们 的讨论也正是基于这⼀点。
在按值传递的情况下，对参数的任何修改，都不会对调⽤端造成任何影响。
那些⽤过按引⽤传递⽅式的⼈可能会在这⼀点上犯糊涂。 
另⼀个让⼈糊涂的地⽅是函数本体内。
如果你只以参数表示“被传递进来的东⻄”，那么代码会清晰得多，因为这种⽤法在所有语⾔中都表现出相同语义。 
在Java中，不要对参数赋值：如果你看到⼿上的代码已经这样做了，请使⽤ “移除对参数的赋值”（131）。
Remove Assignments to Parameters（131）。

当然，⾯对那些使⽤“出参数”的语⾔，你不必进循这条规则。
不过在那些语⾔中我会尽量少⽤出参数。

```java
int discount (int inputVal, int quentity, int yearToDate) {
    if (inputVal > 50) inputVal -= 2;
    ...
}
```
```java

int discount (int inputVal, int quentity, int yearToDate) {
    // 以一个临时变量取代对该参数的赋值。
    int result = inputVal;
    if (inputVal > 50) result -= 2;
    ...
}
```