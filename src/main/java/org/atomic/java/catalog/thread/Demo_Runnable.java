package org.atomic.java.catalog.thread;

/**
 *  1. 在这种方式下，可以多个线程共享同一个目标对象，
 *  所以非常适合多个相同线程来处理同一份资源的情况，
 *  从而可以将CPU代码和数据分开，形成清晰的模型，
 *  较好地体现了面向对象的思想。
 *  2. 可以继续继承别的类
 */

public class Demo_Runnable implements Runnable {

    private int count=10;

    @Override
    public void run(){
        while(count-- > 0){
            System.out.println("T-"+Thread.currentThread().getName()+",count="+count);
        }
    }

    public static void main(String[] args) {
        Demo_Runnable demo = new Demo_Runnable();
        new Thread(demo).start();
        new Thread(demo).start();
    }
}
