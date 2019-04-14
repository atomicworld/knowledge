package org.atomic.java.catalog.singleton;

/**
 *  静态内部类: 延迟加载
 *
 *  利用了 classloder 的机制来保证初始化 instance 时只会有一个。
 *  需要注意的是：虽然它的名字中有“静态”两字，它属于“懒汉模式”的！
 *  这种方式的 Singleton 类被装载时，只要 SingletonHolder 类还没有被主动使用，instance 就不会被初始化。
 *  只有在显式调用 getInstance() 方法时，才会装载 SingletonHolder 类，从而实例化对象。
 */
public class SingletonDemo2 {

    private SingletonDemo2() {}

    public static final SingletonDemo2 getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static final SingletonDemo2 INSTANCE = new SingletonDemo2();
    }
}
