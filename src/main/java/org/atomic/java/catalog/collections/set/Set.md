####Set

set是一种**无序的、可重复**的数据容器，与List一样继承与Collection接口

常使用的：

	HashSet、TreeSet、LinkedHashSet



HashSet：（允许使用 `null` 元素）

```
使用哈希表（hash table）实现的，其中的元素是无序的
HashSet的add、remove、contains方法 的时间复杂度为常量O(1)
```

TreeSet：（）

```
使用树形结构（算法书中的红黑树red-black tree）实现的
TreeSet中的元素是可排序的，但add、remove和contains方法的时间复杂度为O(log(n))
TreeSet还提供了first()、last()、headSet()、tailSet()等方法来操作排序后的集合
```

LinedHashSet：

```
介于HashSet和TreeSet之间。
它基于一个由链表实现的哈希表，保留了元素插入顺序
LinkedHashSet中基本方法的时间复杂度为O(1)。
```

