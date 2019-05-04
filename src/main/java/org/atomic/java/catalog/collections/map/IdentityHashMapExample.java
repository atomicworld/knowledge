package org.atomic.java.catalog.collections.map;

import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMapExample {

    public static void main(String[] args) {

        //IdentityHashMap使用===================================
        Map<String, String> identityHashMap = new IdentityHashMap<>();
        identityHashMap.put(new String("a"), "1");
        identityHashMap.put(new String("a"), "2");
        identityHashMap.put(new String("a"), "3");
        System.out.println(identityHashMap.size()); //3

        Map<Person, String> identityHashMap2 = new IdentityHashMap<>();
        identityHashMap2.put(new Person(1), "1");
        identityHashMap2.put(new Person(1), "2");
        identityHashMap2.put(new Person(1), "3");
        System.out.println(identityHashMap2.size()); //3

        Map<PersonOverWrite, String> identityHashMap3 = new IdentityHashMap<>();
        identityHashMap3.put(new PersonOverWrite(1), "1");
        identityHashMap3.put(new PersonOverWrite(1), "2");
        identityHashMap3.put(new PersonOverWrite(1), "3");
        System.out.println(identityHashMap3.size()); //3

    }
}
