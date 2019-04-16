###List

**定义：**

	List是一个接口，它继承于Collection的接口。它代表着有序的队列

**作用：**

 1. 为什么要用 List list = new ArrayList() ,而不用 ArrayList alist = new ArrayList()呢？

    回答：

    	List接口有多个实现类，现在你用的是ArrayList，也许哪一天你需要换成其它的实现类，如 LinkedList或者Vector等等，这时你只要改变这一行就行了： List list = new LinkedList(); 



**使用：**

	**如果涉及到“栈”、“队列”、“链表”等操作，应该考虑用List**

	**具体的选择哪个List，根据下面的标准来取舍。** 

	(01) 对于需要快速插入，删除元素，应该使用LinkedList。 

	(02) 对于需要快速随机访问元素，应该使用ArrayList。 

	(03) 对于“**单线程环境**” 或者 **多线程环境，但List仅仅只会被单个线程操作**，

		对于“**单线程环境**”，此时应该使用非同步的类(如	ArrayList)。        

		对于“**多线程环境**，且List可能同时被多个线程操作，此时，应该使用同步的类(如Vector)。