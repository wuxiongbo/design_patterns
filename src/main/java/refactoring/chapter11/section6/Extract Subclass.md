# 提炼⼦类




# 动机

使⽤ Extract Subclass（330）的主要动机是：
你发现类中的某些⾏为只被⼀部分实例⽤到，其他实例不需要它们。  

有时候, 这种⾏为上的差异是通过`类型码`区分的，此时,你可以使⽤ Replace Type Code with Subclasses （223） 或 Replace Type Code with State/Strategy （227）。  
但是，并⾮⼀定要出现了 `类型码` 才表示需要考虑使⽤⼦类。  

Extract Class （149）是 Extract Subclass （330）之外的另⼀种选择，
两者之间的抉择其实就是 委托 和 继承 之间的抉择。
Extract Subclass（330）通常更容易进⾏，但它也有限制：⼀旦对象创建完成，你⽆法再改变与类型相关的⾏为。
但如果使⽤ Extract Class （149），你只需插⼊另⼀个组件就可以改变对象的⾏为。此外，⼦类只能⽤以表现⼀组变化。
如果,你希望⼀个类以⼏种不同的⽅式变化，就必须使⽤委托。