package org.atomic.java.catalog.classObj.interface_abstract;

/**
 * 如果继承类，没有具体实现 父类的抽象方法， 不能是 abstract， 那么它自己也必须是 abstract 的；
 */
public class AbstractClassExtends extends AbstractDemo {

    @Override
    public void demo2(){}         //重写方法，抽象方法

    @Override
    protected void demo3(){}       //重写方法， 具体实现

    @Override
    protected void demo4(){}        //重写方法， 具体实现

}
