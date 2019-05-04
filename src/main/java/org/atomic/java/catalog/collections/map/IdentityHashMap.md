### IdentityHashMap



HashMap和IdentityHashMap的区别：

	key值得比较，一个用的是equals（）方法，一个用的是==

1. 比如对于要保存的key，k1和k2，当且仅当k1== k2的时候，IdentityHashMap才会相等，而对于HashMap来说，相等的条件则是：对比两个key的hashCode等
2. IdentityHashMap不是Map的通用实现，它有意违反了Map的常规协定。并且IdentityHashMap允许key和value都为null。
3. 同HashMap，IdentityHashMap也是**无序的**，并且该类**不是线程安全的**，如果要使之线程安全，可以调用Collections.synchronizedMap(new IdentityHashMap(…))方法来实现。

