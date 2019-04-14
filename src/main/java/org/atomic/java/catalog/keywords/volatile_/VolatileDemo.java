package org.atomic.java.catalog.keywords.volatile_;

/**
 *  缓存一致性协议：保证了每个缓存中使用的共享变量的副本是一致的。
 *  它核心的思想是：
 *      当CPU写数据时，如果发现操作的变量是共享变量，即在其他CPU中也存在该变量的副本，
 *      会发出信号通知其他CPU将该变量的缓存行置为无效状态，因此当其他CPU需要读取这个变量时，
 *      发现自己缓存中缓存该变量的缓存行是无效的，那么它就会从内存重新读取。
 *
 *  volatile 关键字
 *  一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：
 *      1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
 *      2）禁止进行指令重排序。
 *
 *  可见性：
 *      第一：使用volatile关键字会强制将修改的值立即写入主存；
 *      第二：使用volatile关键字的话，当线程2进行修改时，会导致线程1的工作内存中缓存变量stop的缓存行无效
 *          （反映到硬件层的话，就是CPU的L1或者L2缓存中对应的缓存行无效）；
 *      第三：由于线程1的工作内存中缓存变量stop的缓存行无效，所以线程1再次读取变量stop的值时会去主存读取。
 *
 *  可见性只能保证每次读取的是最新的值，但是volatile没办法保证对变量的 操作的原子性。
 *
 *  下面这段话摘自《深入理解Java虚拟机》：
 *  “观察加入volatile关键字和没有加入volatile关键字时所生成的汇编代码发现，加入volatile关键字时，会多出一个lock前缀指令”
 *      lock前缀指令实际上相当于一个内存屏障（也成内存栅栏），内存屏障会提供3个功能：
 *          1）它确保指令重排序时不会把其后面的指令排到内存屏障之前的位置，也不会把前面的指令排到内存屏障的后面；
 *              即在执行到内存屏障这句指令时，在它前面的操作已经全部完成；
 *          2）它会强制将对缓存的修改操作立即写入主存；
 *          3）如果是写操作，它会导致其他CPU中对应的缓存行无效。
 *
 *  使用volatile必须具备以下2个条件：（volatile不具备原子性）
 *      1）对变量的写操作不依赖于当前值
 *      2）该变量没有包含在具有其他变量的不变式中
 *
 *
 */
public class VolatileDemo {

    public volatile int inc = 0;

    public void increase() {    //变成synchronized，可以保证原子性
        inc++;
    }

//    Lock lock = new ReentrantLock();  //锁实现方法
//    public  void increase() {
//        lock.lock();
//        try {
//            inc++;
//        } finally{
//            lock.unlock();
//        }
//    }


    public static void main(String[] args) {
        final VolatileDemo test = new VolatileDemo();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println("inc=" +test.inc);
    }

}
