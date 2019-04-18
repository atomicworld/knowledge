### Map

**定义：**

Map是一个接口，通过键和值一一映射. 可以通过键来获取值。

```
1. 给定一个键和一个值，你可以将该值存储在一个Map对象. 之后，你可以通过键来访问对应的值。
2. 当访问的值不存在的时候，方法就会抛出一个NoSuchElementException异常.
3. 当对象的类型和Map里元素类型不兼容的时候，就会抛出一个 ClassCastException 异常。
4. 当在不允许使用Null对象的Map中使用Null对象，会抛出一个 NullPointerException 异常。
5. 当尝试修改一个只读的Map时，会抛出一个UnsupportedOperationException异常。
```



类型介绍：

Java 自带了各种 Map 类。这些 Map 类可归为三种类型：

1. 通用Map，用于在应用程序中管理映射，通常在 java.util 程序包中实现

   HashMap、Hashtable、Properties、LinkedHashMap、IdentityHashMap、TreeMap、WeakHashMap、ConcurrentHashMap

2. 专用Map，通常我们不必亲自创建此类Map，而是通过某些其他类对其进行访问

   java.util.jar.Attributes、javax.print.attribute.standard.PrinterStateReasons、java.security.Provider、java.awt.RenderingHints、javax.swing.UIDefaults

3. 一个用于帮助我们实现自己的Map类的抽象类

   AbstractMap

**常用类型：**

**HashMap**

	最常用的Map，它根据键的HashCode 值存储数据，根据键可以直接获取它的值，具有很快的访问速度。HashMap最多只允许一条记录的键为Null(多条会覆盖);允许多条记录的值为 Null。非同步的。

**TreeMap**

	能够把它保存的记录根据键(key)排序,默认是按升序排序，也可以指定排序的比较器，当用Iterator 遍历TreeMap时，得到的记录是排过序的。TreeMap不允许key的值为null。非同步的。 

**Hashtable**

	与 HashMap类似，不同的是:key和value的值均不允许为null；它支持线程的同步，即任一时刻只有一个线程能写Hashtable，因此也导致了Hashtale在写入时会比较慢。 

**LinkedHashMap**

保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的.在遍历的时候会比HashMap慢。key和value均允许为空，非同步的。 

