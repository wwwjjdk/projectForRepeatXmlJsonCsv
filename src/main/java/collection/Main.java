package collection;

import java.util.*;

public class Main {
    public static void main(String[] args) {
       //generateList();
        //generateTreeMap();
        generateTreeMapWithObj();
    }

    private static  void generateList(){
        Queue<Worker> list = new PriorityQueue<>();
        list.offer(new Worker(5,"b"));
        list.offer(new Worker(3,"j"));
        list.offer(new Worker(10,"c"));

        while (!list.isEmpty()){
            System.out.println(list.poll());
        }
    }

    private static void generateTreeMap(){
        Map<Integer,String> map = new TreeMap<>();
        map.put(1,"1");
        map.put(3,"2");
        map.put(2,"3");
        map.put(6,"3");
        for (Map.Entry<Integer,String> entry: map.entrySet()) {
            System.out.println("k->" + entry.getKey() + " : " + "val->" + entry.getValue());
        }
    }

    private static void generateTreeMapWithObj(){
        Map<Person,String> map = new TreeMap<>(new PersonIdComparator());
        map.put(new Person(6),"6");
        map.put(new Person(3),"3");
        map.put(new Person(10),"10");
        map.put(new Person(4),"4");

        for (Map.Entry<Person,String> entry: map.entrySet()) {
            System.out.println("k->" + entry.getKey() + " : " + "val->" + entry.getValue());
        }

    }
}
