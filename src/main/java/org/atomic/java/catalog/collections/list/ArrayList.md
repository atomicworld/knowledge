###ArrayList

[TOC]

#### 定义：

	ArrayList 是一个**数组队列，相当于动态数组**。由数组实现，随机访问效率高，随机插入、随机删除效率低

**特点：**

	ArrayList是非线程安全



原码中的属性：（java8）

	/**
	* Default initial capacity.
	*/
	private static final int DEFAULT_CAPACITY = 10;
	 默认数组大小
```
/**
 * Shared empty array instance used for empty instances.
 */
private static final Object[] EMPTY_ELEMENTDATA = {};
 初始化实例的空数组
```

```
/**
 * Shared empty array instance used for default sized empty instances. We
 * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
 * first element is added.
 */
private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

1. 构造器使用
public ArrayList() {
	this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}

2. 计算容量的时候
private static int calculateCapacity(Object[] elementData, int minCapacity) {
	if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
		return Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    return minCapacity;
}
……
```

```
/**
 * The array buffer into which the elements of the ArrayList are stored.
 * The capacity of the ArrayList is the length of this array buffer. Any
 * empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
 * will be expanded to DEFAULT_CAPACITY when the first element is added.
 */
transient Object[] elementData;
```

```
/**
 * The size of the ArrayList (the number of elements it contains).
 */
private int size;
```

	

三个构造函数都存在赋值一个空数组给`elementData`，但是为什么无参的赋的是`DEFAULTCAPACITY_EMPTY_ELEMENTDATA`，剩余两个是`EMPTY_ELEMENTDATA`？

	DEFAULTCAPACITY_EMPTY_ELEMENTDATA 与 EMPTY_ELEMENTDATA 分开，这样就可以了解当添加第一个元素时需要创建多大的空间





**扩容：**

	grow(minCapacity)方法，这个就是重头戏

	容量增加为原来的1.5倍的实现

```
int newCapacity = oldCapacity + (oldCapacity >> 1);
```

