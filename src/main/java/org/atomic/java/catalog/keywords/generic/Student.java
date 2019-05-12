package org.atomic.java.catalog.keywords.generic;

import java.util.Arrays;
import java.util.List;

public class Student implements Comparable {
    public String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        //return this.age - ((Student)o).age;
        return (this.name).compareTo(((Student) o).getName());
    }

    //当给定的类型T实现了comparable接口中的方法时，才可以使用泛型进行min的比较。
    //基本类型都已经实现了comparable接口的方法，所以可以之间比较，自己创建的Student类中已实现comparable中的
    //comparedTo方法
    public static <T extends Comparable> T min(List<T> t) {
        T m = t.get(0);
        for(T item : t) {
            if(m.compareTo(item) > 0){
                m = item;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        Student[] ss = {
                new Student("xwt", 1),
                new Student("cm", 2),
                new Student("xww", 3),
                new Student("fdf", 7)
        };
        List<Student> student = Arrays.asList(ss);
        Student minS = min(student);
        System.out.println(minS.getName());
    }

}
