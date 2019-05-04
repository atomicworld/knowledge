package org.atomic.java.catalog.collections.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        Person person1 = new Person("zzh",18);
        Person person2 = new Person("jj",17);
        Person person3 = new Person("qq",19);

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);
        System.out.println("======原始列表：");
        System.out.println(list);

        System.out.println("======use comparable：年龄升序");
        Collections.sort(list);
        System.out.println(list);

        System.out.println("======use comparator-接口： 年龄升序");
        Collections.sort(list, new AscAgeComparator());
        System.out.println(list);

        System.out.println("======use comparator-接口： 年龄降序");
        Collections.sort(list, new DescAgeComparator());
        System.out.println(list);

    }

    public static class DescAgeComparator implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {
            return p2.getAge() - p1.getAge();
        }

    }
}