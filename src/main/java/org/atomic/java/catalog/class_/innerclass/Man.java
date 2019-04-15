package org.atomic.java.catalog.class_.innerclass;

class People {
    public People() {
    }
}

public class Man {
    public Man() {
    }

    public People getWoman() {

        //局部内部类 (定义在一个方法或者一个作用域里面的类)
        class Woman extends People {
            int age = 0;
        }

        return new Woman();
    }
}
