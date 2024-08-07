# Extract Interface（提炼接⼝）

## 动机

类之间彼此互⽤的⽅式有若⼲种。
“使⽤⼀个类“通常意味，⽤到该类的所有责任区。
另⼀种情况是，某⼀组客户只使⽤类责任区中的⼀个特定⼦集。
再⼀种情况则是，这个类需要与所有协助处理某些特定请求的类合作。

对于后两种情况，将真正⽤到的这部分责任分离出来通常很有意义，因为这样可以使系统的⽤法更清晰，同时也更容易看清系统的责任划分。
如果新的类需要⽀持上述⼦集，也⽐较能够看清⼦集内有些什么东⻄。

在许多⾯向对象语⾔中，这种责任划分是通过多继承（multiple inheritance）来实现的。
你可以针对每组⾏为建⽴⼀个类，再将它们组合于同⼀个实现中。
Java只提供单继承（single inheritance），但你可以运⽤接⼝（interface）来昭示并实现上述需求。
接⼝对于Java程序的设计⽅式有着巨⼤的影响，就连Smalltalk程序员都认为接⼝是⼀⼤进步！

Extract Superclass （336）和 Extract Interface（341）之间有些相似之处。
Extract Interface（341）只能提炼共通接⼝，不能提炼共通代码。
使⽤ Extract Interface （341） 可能造成难闻的“重复”坏味道，幸⽽你可以运⽤ Extract Class（149）先把共通⾏为放进⼀个组件中，
然后将⼯作委托该组件，从⽽解决这个问题。
如果有不少共通⾏为，Extract Superclass （336）会⽐较简单，但是每个类只能有⼀个超类。

如果某个类在不同环境下扮演截然不同的⻆⾊，使⽤接⼝就是个好主意。
你可以针对每个⻆⾊以 Extract Interface （341）提炼出相应接⼝。
另⼀种可以⽤上 Extract Interface（341）的情况是：
你想要描述⼀个类的外部依赖接⼝（outbound interface，即这个类要求服务提供⽅提供的操作）。

如果你打算将来加⼊其他种类的服务对象，只需要求它们实现这个接⼝即可。

