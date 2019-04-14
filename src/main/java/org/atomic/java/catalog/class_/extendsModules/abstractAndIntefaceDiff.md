####抽象类和接口的区别

| 比较项             | 抽象类                                                       | 接口                                                         |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 默认方法实现       | 可有普通方法的实现和抽象方法的定义                           | 接口完全是抽象的，它根本不存在方法的实现                     |
| 使用方式           | 子类使用**extends**关键字来继承抽象类。如果子类不是抽象类的话，它需要提供抽象类中所有声明的方法的实现 | 子类使用关键字**implements**来实现接口。它需要提供接口中所有声明的方法的实现 |
| 构造器             | 可以                                                         | 不可以                                                       |
| 与普通Java类的区别 | 除了你不能实例化抽象类之外，它和普通Java类没有任何区别       | 接口是完全不同的类型                                         |
| 访问修饰符         | **public**、**protected**和**default**                       | **public**                                                   |
| main方法           | 可以有main方法，并且可以运行它                               | 没有main方法                                                 |
| 继承               | 一个类只能继承一个抽象类                                     | 一个类可以继承>1个接口                                       |
| 添加新方法         | 如果往抽象类中添加新的方法，可以给它提供默认的实现，因此**不需要改变现有代码** | 如果你往接口中添加方法，那么**必须改变实现该接口的类**       |



#### 什么时候使用抽象类和接口

 	1. 如果你拥有一些方法，并且想拥有它们**默认实现**，那么使用**抽象类**
 	2. 如果你想实现**多重继承**，那么你必须使用**接口**
 	3. 如果**基本功能在不断改变**，那么就需要使用**抽象类**
