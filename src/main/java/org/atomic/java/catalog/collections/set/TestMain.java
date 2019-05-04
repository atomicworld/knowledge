package org.atomic.java.catalog.collections.set;

import java.util.*;

public class TestMain {

    public static void main(String[] args) {

        Set hashSet = new HashSet();
        hashSet.add("hello");
        hashSet.add("world");
        hashSet.add("java");
        hashSet.add("world");
        System.out.println("=======hashSet：");
        Iterator it = hashSet.iterator();
        while(it.hasNext())
            System.out.print(it.next() +" ");
        System.out.println();

        Set hashSet1 = new HashSet();
        hashSet1.add("5");
        hashSet1.add("2");
        hashSet1.add("7");
        hashSet1.add("1");
        System.out.println("=======hashSet：");
        Iterator it1 = hashSet1.iterator();
        while(it1.hasNext())
            System.out.print(it1.next() +" ");
        System.out.println();

        Set treeSet = new TreeSet();
        treeSet.add(1);
        treeSet.add(9);
        treeSet.add(3);
        treeSet.add(5);
        treeSet.add(4);
        System.out.println("=======treeSet：");
        System.out.println(treeSet);

        Set linkedHashSet = new LinkedHashSet();

    }

}
