package org.atomic.java.catalog.class_.extendsModules.abstract_;

/**
 * 必须重写所有的抽象方法
 */
public class ExtendsDefinitionClass extends AbstractDefinitionClass {

    @Override
    public void say() {
        System.out.println("sub class say hello world!");
    }

    @Override
    public void publicAbMethod() {
    }

    @Override
    protected void protectedAbMethod() {

    }

    @Override
    protected void protectedMethod() {

    }

}
