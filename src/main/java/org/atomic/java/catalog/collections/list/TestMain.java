package org.atomic.java.catalog.collections.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class TestMain {

    //线程安全
    List vector = new Vector<>();

    //非线程安全
    List aList = new ArrayList<>();
    List linkedList = new LinkedList<>();



}
