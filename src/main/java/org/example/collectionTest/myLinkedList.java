package org.example.collectionTest;

import java.util.LinkedList;
import java.util.Random;

public class myLinkedList {
    public static void main(String[] args)
    {
        int myTestList=100000;
        LinkedList<String> ll=new LinkedList<>();
        Random rnd=new Random();
        long startT=System.nanoTime();
        for(int i=0;i<myTestList;i++){
            String key="id_"+i;
            String value= String.valueOf(rnd.nextInt(100000));
            ll.add(key+":"+value);
        }
        long endT=System.nanoTime();
        int duration= (int) (endT-startT);
        System.out.println("It takes "+duration+" nmls to build LinkedList of "+myTestList+" elements.");

        //worm-up
        System.out.println("Замір розпочато.");
        for (int i = 0; i < 100000; i++) {
            ll.contains("id_99999");
        }
        long startT1=System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            if(ll.contains("id_99999")){break;};
        }
        long endT1=System.nanoTime();
        long duration1= (endT1-startT1)/1000000;
        System.out.println("It takes "+duration1+" nmls to find [99999] in myLinkedList");
    }

}
