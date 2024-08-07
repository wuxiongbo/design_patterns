# Form TemPlate Method（塑造模板函数）

你有⼀些⼦类，其中相应的某些函数以相同顾序执⾏类似的操作， 但各个操作的细节上有所不同。

将这些操作分别放进独⽴函数中，并保持它们都有相同的签名， 于是原函数也就变得相同了。然后将原函数上移⾄超类。

## 动机

继承是避免重复⾏为的⼀个强⼤⼯具。
⽆论何时，只要你看见两个⼦类之中有类似的函数，就可以把它们提升到超类。
但是如果这些函数并不完全相同该怎么办？ 
我们仍有必要尽量避免重复，但又必须保持这些函数之间的实质差异。

常见的⼀种情况是：两个函数以相同顺序执⾏⼤致相近的操作，但是各操作不完全相同。
这种情况下我们可以将执⾏操作的序列移⾄超，并借助多态保证各操作仍得以保持差异性。
这样的函数被称为 Template Method（模板函数）［Gang of Four］。


