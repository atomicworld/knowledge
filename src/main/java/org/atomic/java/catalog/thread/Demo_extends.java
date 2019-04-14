package org.atomic.java.catalog.thread;


public class Demo_extends extends Thread {

    private int count = 10;

    @Override
    // 在run方法中定义任务
    public void run() {
        while (count-- > 0) {
            System.out.println("T-"+this.getName()+",count="+count);
        }
    }

    public static void main(String[] args) {
        new Demo_extends().start();
        new Demo_extends().start();
    }

}
