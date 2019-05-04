package org.atomic.java.catalog.collections.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("a", "2");
        map.put("a", "3");
        System.out.println(map.size()); //1

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put(new String("a"), "1");
        hashMap.put(new String("a"), "2");
        hashMap.put(new String("a"), "3");
        System.out.println(hashMap.size()); //1

        Map<Integer, String> hashMap2 = new HashMap<>();
        hashMap2.put(new Integer(200), "1");
        hashMap2.put(new Integer(200), "2");
        hashMap2.put(new Integer(200), "3");
        System.out.println(hashMap2.size()); //1

        Map<Person, String> hashMap3 = new HashMap<Person, String>();
        hashMap3.put(new Person(1), "1");
        hashMap3.put(new Person(1), "2");
        hashMap3.put(new Person(1), "3");
        System.out.println(hashMap3.size()); //3

        Map<PersonOverWrite, String> hashMap4 = new HashMap<PersonOverWrite, String>();
        hashMap4.put(new PersonOverWrite(2), "1");
        hashMap4.put(new PersonOverWrite(2), "2");
        hashMap4.put(new PersonOverWrite(2), "3");
        System.out.println(hashMap4.size()); //1
    }

}
