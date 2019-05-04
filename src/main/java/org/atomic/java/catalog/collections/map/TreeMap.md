###TreeMap

**定义：**

	TreeMap基于**红黑树（Red-Black tree）实现**。
	
	该映射根据**其键的自然顺序进行排序**，或者根据**创建映射时提供的 Comparator 进行排序**，具体取决于使用的构造方法。 
	
	TreeMap的基本操作 containsKey、get、put 和 remove 的时间复杂度是 log(n) 。 

特点：

	TreeMap是**非同步**的
	
	它的iterator 方法返回的**迭代器是fail-fastl**的。


```
private final Comparator<? super K> comparator;

private transient Entry<K,V> root;

/**
 * The number of entries in the tree
 */
private transient int size = 0;

/**
 * The number of structural modifications to the tree.
 */
private transient int modCount = 0;
```

