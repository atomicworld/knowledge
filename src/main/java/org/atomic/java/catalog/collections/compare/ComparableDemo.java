package org.atomic.java.catalog.collections.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 比较器：
 * Comparable 相当于 “内部比较器”，
 * Comparator 相当于 “外部比较器”
 */
public class ComparableDemo {

    // Comparable对实现它的每个类的对象进行整体排序，这个接口需要 （类本身去实现）。该类支持排序
    // 若一个类实现了Comparable 接口，实现 Comparable 接口的类的对象的 List 列表 ( 或数组)可以通过 Collections.sort（或 Arrays.sort）进行排序。
    // 此外，实现 Comparable 接口的类的对象 可以用作 “有序映射 ( 如 TreeMap)” 中的键或 “有序集合 (TreeSet)” 中的元素，而不需要指定比较器。
    public interface Comparable<T> {
        public int compareTo(T o);
    }


    //
    public interface Comparator<T> {
        int compare(T o1, T o2);
        boolean equals(Object obj);
    }

    public static void main(String[] args) {
        Person person1 = new Person("zzh",18);
        Person person2 = new Person("jj",17);
        Person person3 = new Person("qq",19);

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);


        System.out.println(list);

        System.out.println("======use comparable-接口：");  //类集成，会被破坏类结构，需要重写 comparedTo()，达到实现抽象方法的
        Collections.sort(list);                            //直接调用 collection 的 sort（）方法，类的排序会加载进去
        System.out.println(list);

        System.out.println("======use comparator-接口："); // 内部定义比较器
        Collections.sort(list, new java.util.Comparator<Person>() {
                    @Override
                    public int compare(Person o1, Person o2) {
                        return o1.getAge() - o2.getAge();
                    }
                });
        System.out.println(list);

    }
}


//实现 comparable 接口，需要重写 compareTo 方法。
class Person implements Comparable<Person> {
    private int age;
    private String name;

    public int getAge(){
        return age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public int compareTo(Person o) {
        return this.age - o.age;
    }
    @Override
    public String toString() {
        return name+":"+age;
    }
}
