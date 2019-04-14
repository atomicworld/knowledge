package org.atomic.java.catalog.singleton;

/**
 *  懒汉模式： 这种写法适用于单线程，多线程会出问题；
 *
 */
public class SingletonDemo1 {

    private volatile static SingletonDemo1 instance = null;

    private SingletonDemo1() {}

    public static SingletonDemo1 getInstance(){     //synchronized 关键字使用在方法上，性能会很差。
        if(instance==null){
                                                        // 唯一的问题是，由于 Java 编译器允许处理器乱序执行，在 JDK 版本小于 1.5 时会有 DCL 失效的问题
            synchronized (SingletonDemo1.class){      //synchronized 只在初始化的时候做一次同步处理
                if(instance==null) {              //多线程 安全问题
                    instance = new SingletonDemo1();
                }
            }

        }
        return instance;
    }

}
