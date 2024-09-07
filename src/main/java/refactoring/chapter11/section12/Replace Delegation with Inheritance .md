



## 动机

本重构与 Replace Inheritance with Delegation （352）恰恰相反。
如果你发现⾃⼰需要使⽤受托类中的所有函数，并且费了很⼤⼒⽓编写所有极简的委托函数，本重构可以帮助你轻松回头使⽤继承。

两条告诚需牢记于⼼。

⾸先，如果你并没有使⽤受托类的所有函数，那么就不应该使⽤ Replace Delegation With Inheritance（35S），
因为⼦类应该总是遵循超类的接⼝。
如果过多的委托函数让你烦⼼，你有别的选择：你可以通过Remove Middle Man （160）让客户端⾃⼰调⽤受托函数，
也可以使⽤Extract Superclass（336）将两个类接⼝相同的部分提炼到超类中，然后让两个类都维承这个新的超类；
你还可以⽤类似的⼿法使⽤Extract Interface （34I）。

另⼀种需要当⼼的情况是：
受托对象被不⽌⼀个其他对象共享，⽽且受托对象是可变的。
在这种情况下，你就不能将委托关系替换为继承关系，因为这样就⽆法 再共享数据了。
数据共享是必须由委托关系承担的⼀种责任，你⽆法把它转给继承 关系。
如果受托对象是不可变的，数据共享就不成问题，因为你⼤可放⼼地复制对象，谁都不会知道。
