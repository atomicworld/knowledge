### HashMap

**定义：**

	HashMap 是一个散列表，它存储的内容是键值对(key-value)映射。

	Hash算法就是根据某个算法**将一系列目标对象转换成地址**，当要获取某个元素的时候，只需要将目标对象做相应的运算获得地址，直接获取。



**特点：**

	HashMap 的实现不是同步的，这意味着它不是线程安全的。

	它的key、value都可以为null

	此外，HashMap中的映射不是有序的



原码相关（java8）

```
/**
* The default initial capacity - MUST be a power of two.
*/
// 初始容量，也就是默认会创建 16 个箱子，箱子的个数不能太多或太少。如果太少，很容易触发扩容，如果太	多，遍历哈希表会比较慢。
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

/**
* The maximum capacity, used if a higher value is implicitly specified
* by either of the constructors with arguments.
* MUST be a power of two <= 1<<30.
*/
// 哈希表最大容量，一般情况下只要内存够用，哈希表不会出现问题
static final int MAXIMUM_CAPACITY = 1 << 30;

/**
* The load factor used when none specified in constructor.
*/
//加载因子（用于扩容）
static final float DEFAULT_LOAD_FACTOR = 0.75f; 

/**
* The bin count threshold for using a tree rather than list for a
* bin.  Bins are converted to trees when adding an element to a
* bin with at least this many nodes. The value must be greater
* than 2 and should be at least 8 to mesh with assumptions in
* tree removal about conversion back to plain bins upon
* shrinkage.
*/
// 上文说过，如果哈希函数不合理，即使扩容也无法减少箱子中链表的长度，因此 Java 的处理方案是当链表太		长时，转换成红黑树。这个值表示当某个箱子中，链表长度大于 8 时，有可能会转化成树
static final int TREEIFY_THRESHOLD = 8;

/**
* The bin count threshold for untreeifying a (split) bin during a
* resize operation. Should be less than TREEIFY_THRESHOLD, and at
* most 6 to mesh with shrinkage detection under removal.
*/
// 在哈希表扩容时，如果发现链表长度小于 6，则会由树重新退化为链表
static final int UNTREEIFY_THRESHOLD = 6;

/**
* The smallest table capacity for which bins may be treeified.
* (Otherwise the table is resized if too many nodes in a bin.)
* Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
* between resizing and treeification thresholds.
*/
// 在转变成树之前，还会有一次判断，只有键值对数量大于 64 才会发生转换。这是为了避免在哈希表建立初	期，多个键值对恰好被放入了同一个链表中而导致不必要的转化
static final int MIN_TREEIFY_CAPACITY = 64;

/**
* Basic hash bin node, used for most entries.  (See below for
* TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
*/
// Entry 实际上就是一个单向链表。这也是为什么我们说HashMap是通过拉链法解决哈希冲突的。
static class Node<K,V> implements Map.Entry<K,V> {
	final int hash;
	final K key;
	V value;
	Node<K,V> next;

	Node(int hash, K key, V value, Node<K,V> next) {
		this.hash = hash;
		this.key = key;
		this.value = value;
		this.next = next;
}

	public final K getKey()        { return key; }
	public final V getValue()      { return value; }
	public final String toString() { return key + "=" + value; }

	public final int hashCode() {
		return Objects.hashCode(key) ^ Objects.hashCode(value);
	}

	public final V setValue(V newValue) {
		V oldValue = value;
		value = newValue;
		return oldValue;
	}

	public final boolean equals(Object o) {
		if (o == this)
			return true;
		if (o instanceof Map.Entry) {
			Map.Entry<?,?> e = (Map.Entry<?,?>)o;
			if (Objects.equals(key, e.getKey()) && 
				Objects.equals(value, e.getValue()))
				return true;
		}
		return false;
	}
}

/* ---------------- Static utilities -------------- */

/**
* Computes key.hashCode() and spreads (XORs) higher bits of hash
* to lower.  Because the table uses power-of-two masking, sets of
* hashes that vary only in bits above the current mask will
* always collide. (Among known examples are sets of Float keys
* holding consecutive whole numbers in small tables.)  So we
* apply a transform that spreads the impact of higher bits
* downward. There is a tradeoff between speed, utility, and
* quality of bit-spreading. Because many common sets of hashes
* are already reasonably distributed (so don't benefit from
* spreading), and because we use trees to handle large sets of
* collisions in bins, we just XOR some shifted bits in the
* cheapest possible way to reduce systematic lossage, as well as
* to incorporate impact of the highest bits that would otherwise
* never be used in index calculations because of table bounds.
*/
static final int hash(Object key) {
	int h;
	return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}

/**
* Returns x's Class if it is of the form "class C implements
* Comparable<C>", else null.
*/
static Class<?> comparableClassFor(Object x) {
	if (x instanceof Comparable) {
        Class<?> c; Type[] ts, as; Type t; ParameterizedType p;
        if ((c = x.getClass()) == String.class) // bypass checks
            return c;
        if ((ts = c.getGenericInterfaces()) != null) {
            for (int i = 0; i < ts.length; ++i) {
                if (((t = ts[i]) instanceof ParameterizedType) &&
                    ((p = (ParameterizedType)t).getRawType() ==
                    Comparable.class) &&
                    (as = p.getActualTypeArguments()) != null &&
                    as.length == 1 && as[0] == c) // type arg is c
                    	return c;
        	}
		}
	}
	return null;
}

/**
* Returns k.compareTo(x) if x matches kc (k's screened comparable
* class), else 0.
*/
@SuppressWarnings({"rawtypes","unchecked"}) // for cast to Comparable
static int compareComparables(Class<?> kc, Object k, Object x) {
	return (x == null || x.getClass() != kc ? 0 :
			((Comparable)k).compareTo(x));
}

/**
* Returns a power of two size for the given target capacity.
*/
static final int tableSizeFor(int cap) {
	int n = cap - 1;
	n |= n >>> 1;
	n |= n >>> 2;
	n |= n >>> 4;
	n |= n >>> 8;
	n |= n >>> 16;
	return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
}

/* ---------------- Fields -------------- */

/**
* The table, initialized on first use, and resized as
* necessary. When allocated, length is always a power of two.
* (We also tolerate length zero in some operations to allow
* bootstrapping mechanics that are currently not needed.)
*/
transient Node<K,V>[] table;	// 数据存储数组

/**
* Holds cached entrySet(). Note that AbstractMap fields are used
* for keySet() and values().
*/
transient Set<Map.Entry<K,V>> entrySet;

/**
* The number of key-value mappings contained in this map.
*/
transient int size;	// 长度、大小

/**
* The number of times this HashMap has been structurally modified
* Structural modifications are those that change the number of mappings in
* the HashMap or otherwise modify its internal structure (e.g.,
* rehash).  This field is used to make iterators on Collection-views of
* the HashMap fail-fast.  (See ConcurrentModificationException).
*/
transient int modCount;

/**
* The next size value at which to resize (capacity * load factor).
*
* @serial
*/
// (The javadoc description is true upon serialization.
// Additionally, if the table array has not been allocated, this
// field holds the initial array capacity, or zero signifying
// DEFAULT_INITIAL_CAPACITY.)
int threshold;	// 极限值，阈值。 初始值 = 数组长度 * 加载因子

/**
* The load factor for the hash table.
*
* @serial
*/
final float loadFactor;
```



	事实上Java的数据无非就三种，**基本类型，引用类型(类似C里面的指针类型）和数组**，有些地方说是2种类型，只有**引用类型和数组**，通过这三种数据类型可以构建出任何数据结构。

	在Java中，**ArrayList这种底层就是用Objec数组来构建的，而HashMap也是用数组来构建，只不过数据数组的数据类型是一个叫做Entry的内部类来保存key、value、hash(不是hashCode)和next(也就是链表的下一个元素)**。

	其实HashSet也是HashMap，只不过比较特殊，没有使用Entry的value而只用了key而已。看看HashSet的构造方法：

```
public HashSet() {
    map = new HashMap<E,Object>();
}
```



基础实现：

	由于通过hash算法产生的逻辑地址可能导致冲突，所以对于一个长度为length的数组，里面存放小于length个数据元素的时候就有可能出现冲突的现象，因为比如说要在长度为16的数组中存放字符串(也就是一个空的HashMap默认的长度)，每个字符串通过调用自身的hashCode()方法会得到该字符串的hashCode，然后通过HashMap的这两个方法会算出在数组中的位置，也就是下面的 i。

```
int hash = hash(key.hashCode());
int i = indexFor(hash, table.length);
```

	任意字符串的hashCode通过上面2个方法都会得到一个i，这个i就是在数组中的位置，这里比较巧妙的设计就是indexFor(hash,table.length)这个方法：

```
static int indexFor(int h, int length) {
   return h & (length-1);
}
```

	这个方法里面的**任意h与(length-1)做位运算**之后得到的值始终都是在length之内的，也就是在数组table之内，因为拿任意一个数来和另一个数来做与运算，结果肯定是小于等于较小的哪一个数。

	与此同时，既然字符串调用hashCode()会得到一个值，那么就会出现不相同的字符串调用hashCode方法之后得到的值是一样的，这种可能性是存在的，而且几乎肯定是存在的。

	这时候就需要在数组的**某个位置增加一个链表结构，用户存储相同的hashCode的字符串**，而这个时候HashMap的size同样也会自增1，尽管这2个字符串只有一个存在于数组中。

HashMap中的size变量有两个作用，第一是通过调用size()方法来返回map的长度，

```
public int size() {
   return size;
}
```

	第二个作用相当重要，就是解决hash算法的核心力量，解决冲突。

	在HashMap的构造方法中可以看出，**hashmap的长度和底层数组table都是capacity**，但是还有一个变量叫做**threshold，极限值，阈值**的意思，默认情况的构造方法：

```
public HashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR;
    threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    table = new Entry[DEFAULT_INITIAL_CAPACITY];
    init();
}
```

	这个**阈值**就是**数组长度和加载因子的乘积**，这东西有什么用呢，假设都按照默认情况来看，默认构造方法构造出来的hashmap长度为16，底层数组长度也为16，而**阈值threshold长度为12，因为默认加载因子是0.75。**

	也就是说当箱map中存放12个元素是，map的结构没什么变化，但是当存储第13个的时候，table就需要扩容了，扩大为原来的2倍。

	如果加载因子是1，那么map中存放13个的时候他是不会扩容的，table.length = 16；而为0.75的时候，存放13个数据的时候table.length = 32。

	那么同样是存放13个数据，分别在长度为16的数组和32的数组中存放，出现冲突的几率：一般来说16的数组要大一些，那为什么会大一些呢，因为某个数据存放进入数组的位置是根据

```
int hash = hash(key.hashCode());
int i = indexFor(hash, table.length);
```

	这两个方法算出来的，其中就包括table.length，换句话说，位置i 跟hash和table.length是相关的，也就是说位置i与table.length是联动的，换个角度，存放的13个数据假设是固定的，而得出hashCode的算法也是固定的，那么位置i 就只跟length的大小有关联了；

	**一般来说length越大，数据的冲突几率就低一些，在map.getValue(key)的时候需要在链表中比较的次数就少一些，性能就高一些。**

	这就是Java中hashmap的性能因素：

	**一般来说，加载因子factor越大，同样个数的数据所占用空间就越小，table.length就越小，空间利用率高，冲突几率就越大；加载因子factor越小，同样个数的数据所占用空间就越大，table.length越大，空间利用率低，冲突的几率小**

	类比一下，比如你地上放了10个碗，你手里面握了10颗大米，你撒下去，前提是必须10颗米都要撒进碗里，你是不是会发现有些碗里面装了两颗三颗，而有些碗是空的，接下来，你在地上摆20个碗，还是撒10颗米下去，依然是所有的米都要进碗，依然还是会出现有些碗是空的，有些是一颗两颗三颗这种现象，但是很明显一般来讲20个碗的时候每个碗里面装不止一颗的情况要比10个碗的情况要少。

	当然也不一定完全是这样，但是一般来说是这样，这就是hash算法，如果设计的好的情况下我们希望每个碗里面都最多放一颗进去，但是这种情况比较少见，但不管怎么说，按照普遍情况来看，20个碗的装多颗的情况是比10个碗装多颗的情况要少一点。

	从数据结构的角度来说叫做用**空间换时间的策略**，以空间换时间何止hash算法，**双向链表也是用空间换时间的策略**

	至于说为什么默认是0.75，我估计这个是前辈们和科学家们总结出来的一个这种的办法，空间利用率比较不错，同时性能比较令人接受吧
	顺便说一下啊，当我们不断的往一个hashmap里面添加数据的时候，**如果超过某个阈值，他就会扩容，扩容的同时会让之前的所有元素重新生成地址**，并且把原来的数组里面的数据迁移到新的数组中(新的数组容量是原来的两倍长度)。

	顺便说下，这个数据迁移其实对性能损耗还是相当大的，毕竟你是要复制数组，同时要重新构建每个元素的在table中的位置

	**因此我们可以在使用hashMap之前大概的估算一下这个hashMap里面大概会存多少个元素，这样就可以在new hashmap的时候就给定他的容量，这样数据迁移的次数相对就少一些，性能就更好一点**。



扩容的数学解释：

https://www.cnblogs.com/chinajava/p/5808416.html





> 有两个字典，分别存有 100 条数据和 10000 条数据，如果用一个不存在的 key 去查找数据，在哪个字典中速度更快？

有些计算机常识的读者都会立刻回答: “一样快，底层都用了哈希表，查找的时间复杂度为 O(1)”。然而实际情况真的是这样么？

	答案是否定的，存在少部分情况两者速度不一致，本文首先对哈希表做一个简短的总结，然后思考 Java 和 Redis 中对哈希表的实现，最后再得出结论，如果对某个话题已经很熟悉，可以直接跳到文章末尾的对比和总结部分。



