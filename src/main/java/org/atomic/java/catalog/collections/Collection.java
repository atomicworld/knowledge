package org.atomic.java.catalog.datatype.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  Set: HashSet、TreeSet
 *  Map: HashMap、HashTable、TreeMap
 *  List: ArrayList、LinkedList、Vector、Stack
 *  Iterator（ Iterator it = collection.iterator() ）hasNext、next、remove
 */
public class Collection {

    public static ConcurrentHashMap cHashMap = new ConcurrentHashMap();

    //Entry数组组成的链，hash()计算
    public void HashMapTest(){
        // From the source Code we know that "unsynchronized and permits nulls"
        HashMap<String, String> map = new HashMap<>();
        map.put("tom", "teacher");
        map.put("jack", "student");
        map.put(null, null);    //permit set null( key=null and value=null )
        int size = map.size();
        System.out.println(size);//size = 3
        System.out.println(map.get(null));//null

        //for(Entry<String> entry:map.entrySet())
        Iterator it = map.entrySet().iterator();    //map.keySet().iterator()   need load more data for twice
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key+":"+val);//null:null,tom:teacher,jack:student
        }
    }

    public void HashtableTest(){
        // From the source Code we know that "synchronized and not permits nulls"
        // lots of methods is used "synchronized"
        Hashtable<String, String> hashTable = new Hashtable<>();
        //this will throw NullPointerException
        //hashTable.put(null, null);    //if (value == null)
        //hashTable.put(null, "teacher"); //int hash = key.hashCode();
        hashTable.put("jack", "student");
        int size1 = hashTable.size();
        System.out.println(size1);

        Enumeration e = hashTable.keys();   //遍历key
        while( e.hasMoreElements() ){
            System.out.println( e.nextElement() );
        }
        e = hashTable.elements();   //遍历value
        while( e.hasMoreElements() ) {
            System.out.println(e.nextElement());
        }
    }


    //Hashtable 锁住整个hash表
    //ConcurrentHashMap，锁住hash表中的Segment段，控制整个hash表；有些方法需要跨段，比如size()和containsValue().[按顺序锁定所有段]
    //ConcurrentHashMap（整个Hash表）,Segment（桶），HashEntry（节点）
    public void ConcurrentHashMapTest(){
        ConcurrentHashMap cHashMap = new ConcurrentHashMap(); //内部方法使用
    }

    //ArrayList 基于Object[]数组
    //查询快速，根据下表index；新增、修改需要找到插入和删除位置，然后复制后面的数据。
    public void ArraListTest(){
        ArrayList<String> arrayList = new ArrayList<>();
    }

    //基于链表形式，有节点指向next；
    //插入、删除都比较快速，修改next指向；
    public void LinkedListTest(){
        LinkedList<String> linkedList = new LinkedList<>();
    }
    //具体速度还得具体分析，不过推荐ArrayList优先。

    //synchronized 线程安全的
    //跟ArrayList类似，Object[]；
    public void Vector(){
        Vector<String> vector = new Vector<>();
    }

    //继承自Vector
    public void Stack(){
        Stack<String> stack = new Stack<>();
    }


    //
    public void HashSetTest(){
        HashSet<String> hashSet = new HashSet<>();
    }
    public void TreeSetTest(){
        TreeSet<String> treeSet = new TreeSet<>();
    }


    public static void main(String[] args){
        new Thread("Thread3"){
            @Override
            public void run() {
                try{
                    cHashMap.put(3, 33);
                    this.sleep(50000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread("Thread2"){
            @Override
            public void run() {
                try {
                    cHashMap.put(2, 22);
                    this.sleep(50000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread("Thread4"){
            @Override
            public void run() {
                try{
                    cHashMap.put(4, 44);
                    this.sleep(50000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread("Thread7"){
            @Override
            public void run() {
                try{
                    cHashMap.put(7, 77);
                    this.sleep(50000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();
        System.out.println(cHashMap);
    }

}
