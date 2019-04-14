package org.atomic.java.catalog.keywords.synchronized_;

/**
 *  synchronized方法 、synchronized块
 *
 *  1.当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。
 *      另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
 *  2.当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
 *  3.当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
 *
 *  这里要注意：
 *      所有的非静态同步方法用的都是同一把锁——实例对象本身
 *      而所有的静态同步方法用的也是同一把锁——类对象本身
 *      对于同步块，由于其锁是可以选择的，所以只有使用同一把锁的同步块之间才有着竞态条件
 *
 */
public class SynchronizedDemo {
}
