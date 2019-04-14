package org.atomic.java.catalog.class_.extendsModules.abstract_;

public abstract class AbstractDefinitionClass {

    //可以有静态代码块
    {
        System.out.println("hello, interface");
    }

    private String var = "hello";               //可以存在 一般的成员变量
    public static final String var2 = "test";   //可以存在 静态不可修改的成员变量

    public void say() {                        //可以存在 一般的方法
        System.out.println("hello world!");
    }

    public abstract void publicAbMethod();      //可以存在 public 的抽象方法

    protected abstract void protectedAbMethod(); //可以存在 protected 的抽象方法，因为可以给子类继承。（extends）

    protected void protectedMethod() {           //可以存在 protected 的方法，因为可以给子类继承。（extends）
    }

    public static void main(String[] args) {
        AbstractDefinitionClass abDefinition = new AbstractDefinitionClass() {
            @Override
            public void publicAbMethod() {
            }

            @Override
            protected void protectedAbMethod() {
            }
        };
    }
}
