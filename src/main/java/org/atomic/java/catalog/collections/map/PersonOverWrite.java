package org.atomic.java.catalog.collections.map;

public class PersonOverWrite {

    private int id;

    public PersonOverWrite() {}

    public PersonOverWrite(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
