package org.atomic.java.catalog.singleton;

/**
 *  饿汉模式： 初始化比较早
 */
public class SingletonDemo3 {
    private static SingletonDemo3 instance = null;

    static {
        instance = new SingletonDemo3();
    }

    private SingletonDemo3() {}

    public static SingletonDemo3 getInstance() {
        return instance;
    }
}
