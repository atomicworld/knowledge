#### ConcurrentHashMap

- 底层采用分段的数组 + 链表实现，线程**安全**
- 通过把整个Map分为N个Segment，可以提供相同的线程安全，但是效率提升N倍，默认提升16倍。(读操作不加锁，由于HashEntry的value变量是 volatile的，也能保证读取到最新的值。)
- Hashtable的synchronized是针对整张Hash表的，即每次锁住整张表让线程独占，ConcurrentHashMap允许多个修改操作并发进行，其关键在于使用了锁分离技术
- 有些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而而不仅仅是某个段，这需要按顺序锁定所有段，操作完毕后，又按顺序释放所有段的锁
- 扩容：段内扩容（段内元素超过该段对应Entry数组长度的75%触发扩容，不会对整个Map进行扩容），插入前检测需不需要扩容，有效避免无效扩容



特性：

ConcurrentHashMap基本上就是线程安全的HashMap

ConcurrentHashMap主要利用CAS方法保证了线程的安全性，

这里只简要介绍一下CAS。CAS的全称是compare and swap，原理很简单，四个参数：

	要修改的对象，内存值，预期值，修改后的值。

	拿内存值与预期值对比，如果一样就为修改的对象赋上修改后的值，如果不一样就不动。





源码：

```
/**
 * The largest possible table capacity.  This value must be
 * exactly 1<<30 to stay within Java array allocation and indexing
 * bounds for power of two table sizes, and is further required
 * because the top two bits of 32bit hash fields are used for
 * control purposes.
 */
 //最大容量为2的30次方
private static final int MAXIMUM_CAPACITY = 1 << 30;

/**
 * The default initial table capacity.  Must be a power of 2
 * (i.e., at least 1) and at most MAXIMUM_CAPACITY.
 */
 //默认大小为16
private static final int DEFAULT_CAPACITY = 16;

/**
 * The largest possible (non-power of two) array size.
 * Needed by toArray and related methods.
 */
 
static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

/**
 * The default concurrency level for this table. Unused but
 * defined for compatibility with previous versions of this class.
 */
private static final int DEFAULT_CONCURRENCY_LEVEL = 16;

/**
 * The load factor for this table. Overrides of this value in
 * constructors affect only the initial table capacity.  The
 * actual floating point value isn't normally used -- it is
 * simpler to use expressions such as {@code n - (n >>> 2)} for
 * the associated resizing threshold.
 */
 //负载参数为0.75
private static final float LOAD_FACTOR = 0.75f;

/**
 * The bin count threshold for using a tree rather than list for a
 * bin.  Bins are converted to trees when adding an element to a
 * bin with at least this many nodes. The value must be greater
 * than 2, and should be at least 8 to mesh with assumptions in
 * tree removal about conversion back to plain bins upon
 * shrinkage.
 */
 //链表转换红黑树节点数阈值为8
static final int TREEIFY_THRESHOLD = 8;

/**
 * The bin count threshold for untreeifying a (split) bin during a
 * resize operation. Should be less than TREEIFY_THRESHOLD, and at
 * most 6 to mesh with shrinkage detection under removal.
 */
 //红黑树转换链表节点数阈值为6
static final int UNTREEIFY_THRESHOLD = 6;

/**
 * The smallest table capacity for which bins may be treeified.
 * (Otherwise the table is resized if too many nodes in a bin.)
 * The value should be at least 4 * TREEIFY_THRESHOLD to avoid
 * conflicts between resizing and treeification thresholds.
 */
 //链表转换红黑树容量阈值为64（Map容量不到64时，链表转红黑树之前会先扩容）
static final int MIN_TREEIFY_CAPACITY = 64;

/**
 * Minimum number of rebinnings per transfer step. Ranges are
 * subdivided to allow multiple resizer threads.  This value
 * serves as a lower bound to avoid resizers encountering
 * excessive memory contention.  The value should be at least
 * DEFAULT_CAPACITY.
 */
 //每个cpu强制处理的最小Map容量数
private static final int MIN_TRANSFER_STRIDE = 16;

/**
 * The number of bits used for generation stamp in sizeCtl.
 * Must be at least 6 for 32bit arrays.
 */
 
private static int RESIZE_STAMP_BITS = 16;

/**
 * The maximum number of threads that can help resize.
 * Must fit in 32 - RESIZE_STAMP_BITS bits.
 */
 //参与扩容的最大线程数
private static final int MAX_RESIZERS = (1 << (32 - RESIZE_STAMP_BITS)) - 1;

/**
 * The bit shift for recording size stamp in sizeCtl.
 */
 //移位量，把生成戳移位后保存在sizeCtl中当做扩容线程计数的基数，相反方向移位后能够反解出生成    
    戳（抄的）
private static final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;
```

重要成员:

```
 //Map对应的Hash桶数组
    transient volatile Node<K,V>[] table;

     //扩容时候新建的Hash桶数组，注意transient关键字，该字段不会被序列化
    private transient volatile Node<K,V>[] nextTable;
    
    //用于节点计数
    private transient volatile long baseCount;

    //非常非常非常重要的一个参数，统御全局
    //sizeCtl = -1，表示有线程正在进行初始化操作，防止多线程同时初始化Map  
    //sizeCtl = -(1 + nThreads)，表示有nThreads个线程正在进行扩容操作  
    //sizeCtl > 0，表示接下来的初始化操作中的Map容量，或者表示初始化/扩容完成后的阈值
    //sizeCtl = 0，默认值
    private transient volatile int sizeCtl;

    //用以维护多线程扩容时候的线程安全
    private transient volatile int transferIndex;
```

