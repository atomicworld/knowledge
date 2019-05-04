package org.atomic.java.catalog.collections.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TestMain {

    public static void main(String[] args) {

        Map map = new HashMap<String, String>();

        Map linkedMap = new LinkedHashMap<String, String>();

        Map identityHashMap = new IdentityHashMap<String, String>();

        Map treeMap = new TreeMap<String, String>();

        Map concurrentHashMap = new ConcurrentHashMap();

    }

}
