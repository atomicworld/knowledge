package org.atomic.java.catalog.collections.compare;

import java.util.Comparator;

public class DescAgeComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return p2.getAge() - p1.getAge();
    }

}
