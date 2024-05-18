# 移除对参数的赋值
代码对⼀个参数进⾏赋值。 

修改前:
```java
int discount(int inputVal, int quantity, int yearToDate) {
    if (inputVal > 50) inputVal -= 2;
    //...
    return inputVal;
}
```
修改后: 以一个临时变量取代对该参数的赋值。
```java
int discount(int inputVal, int quantity, int yearToDate) {
    // 以一个临时变量取代对该参数的赋值。
    int result = inputVal;
    if (inputVal > 50) result -= 2;
    //...
    return result;
}
```

## 动机
⾸先，我要确定⼤家都清楚 “对参数赋值” 这个说法的意思。
如果你把⼀个名为foo的对象作为参数传给某个函数，那么 “对参数赋值”意味改变foo，使它引⽤另⼀个对象。
如果你在“被传⼊对象”⾝上进⾏什么操作，那没问题，我也总是这样⼲。

我只针对 “foo被改⽽指向另⼀个对象” 这种情况来讨论：

```java
void aMethod(Object foo) {
    foo.modifyInSomeWay();  // that's OK
    foo = anotherobject;    // trouble and despair will follow you
}
```
我之所以不喜欢这样的做法，因为它降低了代码的清晰度，⽽且混⽤了按值传递和按引⽤传递这两种参数传递⽅式。
Java只采⽤按值传递⽅式（稍后讨论），我们的讨论也正是基于这⼀点。
在按值传递的情况下，对参数的任何修改，都不会对调⽤端造成任何影响。
那些⽤过按引⽤传递⽅式的⼈可能会在这⼀点上犯糊涂。 
另⼀个让⼈糊涂的地⽅是函数本体内。
如果你只以参数表示“被传递进来的东⻄”，那么代码会清晰得多，因为这种⽤法在所有语⾔中都表现出相同语义。 
在Java中，不要对参数赋值：如果你看到⼿上的代码已经这样做了，请使⽤ “移除对参数的赋值”（131）。
Remove Assignments to Parameters（131）。

当然，⾯对那些使⽤“出参数”的语⾔，你不必进循这条规则。
不过在那些语⾔中我会尽量少⽤出参数。


## 做法
-[ ] 建⽴⼀个临时变量，把待处理的参数值赋予它。
-[ ] 以“对参数的赋值”为界，将其后所有对此参数的引⽤点，全部替换为“对此临时变量的引⽤”。 
-[ ] 修改賦值语句，使其改为对新建之临时变量賦值。 
-[ ] 编译，测试。 
  - 如果代码的语义是按引⽤传递的，请在调⽤端检查调⽤后是否还使⽤了这个参数。
    也要检查有多少个按引⽤传递的参数被賦值后⼜被使⽤。请尽量只以`return`⽅式返回⼀个值。
  - 如果需要返回的值不⽌⼀个，看看可否把需返回的⼤堆数据变成单⼀对象，或⼲脆为每个返回值设计对应的⼀个独⽴函数。

## 范例

```java
int discount(int inputVal,int quantity,int yearToDate) {
    if(inputVal > 50) inputVal -= 2;
    if (quantity > 100) inputVal -= 1;
    if (yearToDate > 10000) inputVal -= 4;
    return inputVal;
}
```

以临时变量取代对参数的赋值动作，得到下列代码：
```java
int discount(int inputVal, int quantity, int yearToDate){
    int result = inputval;
    if (inputVal >50) result -= 2;
    if (quantity >100) result -= 1;
    if (yearTopate>10000) result -= 4;
    return result;
}
```
还可以为参数加上关键词`final`，从⽽强制它遵循“不对参数赋值”这⼀惯例：
```java
int discount(final int inputVal, final int quantity,final int yearToDate){
    int result = inputval;
    if (inputVal >50) result -= 2;
    if (quantity >100) result -= 1;
    if (yearTopate>10000) result -= 4;
    return result;
}
```

不过我得承认，我并不经常使⽤`final`来修饰参数，因为我发现，对于提⾼**短函数**的清晰度，这个办法并⽆太⼤帮助。  
我通常会在较⻓的函数中使⽤它，让它帮助我检查参数是否被做了修改。
