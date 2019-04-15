package org.atomic.java.catalog.class_.clone.shallow;

public class TestClone {

    public static void main(String[] args) {
        Age age = new Age(20);
        Student stu1 = new Student("Jack", age, 175);

        Student stu2 = (Student) stu1.clone();
        System.out.println(stu1.toString());
        System.out.println(stu2.toString());


        stu1.setName("Tom");    //尝试修改stu1中的各属性，观察stu2的属性有没有变化
        age.setAge(99);           //改变age这个引用类型的成员变量的值
        /**
         * stu1.setaAge(new Age(99));
         * 使用这种方式修改age属性值的话，stu2是不会跟着改变的。
         * 因为创建了一个新的Age类对象而不是改变原对象的实例值
         */
        stu1.setLength(216);
        System.out.println(stu1.toString());
        System.out.println(stu2.toString());
    }

}
