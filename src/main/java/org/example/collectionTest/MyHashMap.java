package org.example.collectionTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MyHashMap {
    public static void main(String[] args)
    {
        int myTestList=100000;
        int myCapacity=262144;
        Map<String,String> myHashMap= new HashMap<>(262144);
        Random rnd=new Random();
        long startT=System.nanoTime();
        for(int i=0; i<myTestList; i++){
            String key= "id_"+String.valueOf(i);
            String value= String.valueOf(rnd.nextInt(100000));
            myHashMap.put(key,value);
        }
        long endT=System.nanoTime();
        long duration=endT-startT;
        System.out.println("Size of myHashMap= "+ myHashMap.size());
        System.out.println("It takes "+ duration+" nmls to build HashMap of "+myTestList +" elements with myCapacity = "+myCapacity);

        //worm-up
        for (int i = 0; i < 300000; i++) {
            myHashMap.containsKey("id_99999");
        }
        //Замір часу на пошук елемента
        long startT2=System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            myHashMap.containsKey("id_99999");
        }
        long endT2=System.nanoTime();
        long duration2=(endT2-startT2)/1000000;
        System.out.println("It takes "+duration2+" nmls to find [id_99999] in HashMap with myCapacity = "+myCapacity);
        //myHashMap=null;

   /*          Map<String,String> myHashMapNoCapacity= new HashMap<>();
        Random rnd1=new Random();
        long startT1=System.currentTimeMillis();
        for(int i=0; i<100000; i++){
            String key= "id_"+String.valueOf(i);
            String value= String.valueOf(rnd1.nextInt(100000));
            myHashMapNoCapacity.put(key,value);
        }
        long endT1=System.currentTimeMillis();
        long duration1=endT1-startT1;

        System.out.println("Size of myHashMapNoCapacity= "+ myHashMapNoCapacity.size());
        System.out.println("duration1 for myHashMapNoCapacity = "+duration1);
        myHashMapNoCapacity=null;

*/
    }
}
