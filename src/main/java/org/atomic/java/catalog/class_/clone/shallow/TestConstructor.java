package org.atomic.java.catalog.class_.clone.shallow;

public class TestConstructor {
    public static void main(String[] args) {

        Age age = new Age(20);

        Person p1 = new Person(age, "Jack");
        Person p2 = new Person(p1); //通过拷贝构造方法实现浅拷贝

        System.out.println("person_1= " + p1);
        System.out.println("person_2= " + p2);

        //修改p1的各属性值，观察p2的各属性值是否跟随变化
        p1.setName("Tom");
        age.setAge(99);

        System.out.println("修改后的person_1= " + p1);
        System.out.println("修改后的person_2= " + p2);

        /**
         * Age 是引用
         * name 是属性（基本数据）
         */
    }
}
