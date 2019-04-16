###LinkedList

**定义：**

	是一个继承于AbstractSequentialList的双向**链表**。它也可以被当作堆栈、队列或双端队列进行操作

	链表数据结构的特点是每个元素分配的空间不必连续、插入和删除元素时速度非常快、但访问元素的速度较慢

```
private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
```



**特点：**

	不是线程安全的



原码中的属性：（java8）

	transient int size = 0;
	/**
	* Pointer to first node.
	* Invariant: (first == null && last == null) ||
	*            (first.prev == null && first.item != null)
	*/
	transient Node<E> first;
	
	/**
	* Pointer to last node.
	* Invariant: (first == null && last == null) ||
	*            (last.next == null && last.item != null)
	*/
	transient Node<E> last;


如何将“**双向链表和索引值联系起来的**”？     

	实际原理非常简单，它就是通过一个**计数索引值**来实现的。

	例如，当我们调用get(int location)时，首先会比较“location”和“双向链表长度的1/2”；

	若前者大，则从链表头开始往后查找，直到location位置；

	否则，从链表末尾开始先前查找，直到location位置。    

	这就是“双线链表和索引值联系起来”的方法。

```
if (index < (size >> 1)) {
	Node<E> x = first;
    for (int i = 0; i < index; i++)
    	x = x.next;
        return x;
} else {
	Node<E> x = last;
    for (int i = size - 1; i > index; i--)
    	x = x.prev;
        return x;
	}
}
```



**扩容：**

	没有初始化大小，也没有扩容的机制，就是一直在前面或者后面新增就好。

