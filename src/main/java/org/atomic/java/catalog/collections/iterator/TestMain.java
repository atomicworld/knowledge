package org.atomic.java.catalog.collections.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        List list = new ArrayList<String>();

        list.add("Bob");
        list.add("Amy");
        list.add("Jack");
        list.add("Tom");
        list.add("Perana");

        Iterator it = list.iterator();
        while (it.hasNext()) {
            String item = (String) it.next();
            System.out.println(item);
        }

        System.out.println(it.hasNext());

    }

}
