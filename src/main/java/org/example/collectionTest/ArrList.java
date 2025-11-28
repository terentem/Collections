package org.example.collectionTest;

import java.util.ArrayList;
import java.util.Random;

public class ArrList {
    public static void main(String[] args)
    {
        int myTestList=100000;
        int myCapacity=110242;
        ArrayList<String> arrL = new ArrayList<>(110242);
        Random rnd=new Random();
        long startT=System.nanoTime();
        for(int i=0; i<myTestList;i++)
        {
            String key= "id_"+String.valueOf(i);
            String value= String.valueOf("test_"+i);
            arrL.add(key+":"+value);
        }
        long endT=System.nanoTime();
        long duration=endT-startT;

        System.out.println("It takes "+duration+" nmls to build myArrList of "+myTestList+" elements.");


        //Замір часу на пошук елемента по contains
        //worm-up
        for (int i = 0; i < 100000; i++) {
            arrL.contains("id_99999:test_99999");
        }
        System.out.println("Замір по contains розпочато.");
        long startT1=System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            if(arrL.contains("id_99999:test_99999"));}

        long endT1=System.nanoTime();
        long duration1=(endT1-startT1)/1000000;
        System.out.println("It takes "+duration1+" nmls on an average to find [99999] in myArrList by contains. [99999]= "+arrL.get(99999));


        //Замір часу на пошук елемента по get(index)
        //worm-up
        for (int i = 0; i < 1000000; i++) {
           arrL.get(99999);
        }
        System.out.println("Замір по get(index) розпочато.");
        long startT2=System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            arrL.get(99999);
        }
        long endT2=System.nanoTime();
        long duration2=(endT2-startT2)/1000000;
        System.out.println("It takes "+duration2+" nmls on an average to find [99999] in myArrList by get(index).");

        //Замір часу на пошук елемента по remove(index)
        //worm-up
        for (int i = 0; i < 1000000; i++) {
            arrL.set(0, arrL.get(0));
            arrL.size();
        }
        System.out.println("Замір по remove(index) розпочато.");
        long startT3=System.nanoTime();
        arrL.remove(0);
        long endT3=System.nanoTime();
        long duration3=(endT3-startT3);
        System.out.println("It takes "+duration3+" nmls to remoove [0] in myArrList by romove(index).");
        arrL=null;


        //Замір часу на пошук елемента по remove(object)
        //Створюємо заново arrl
        ArrayList<String> arrL1 = new ArrayList<>(110242);
        for(int i=0; i<myTestList;i++)
        {
            String key= "id_"+String.valueOf(i);
            String value= String.valueOf(rnd.nextInt(100000));
            arrL1.add(key+":"+value);
        }
        //worm-up
        for (int i = 0; i < 1000000; i++) {
            arrL1.contains("id_99999");
            arrL1.size();
        }
        System.out.println("Замір по remove(object) розпочато.");
        long startT4=System.nanoTime();
        arrL1.remove("id_99999");
        long endT4=System.nanoTime();
        long duration4=(endT4-startT4);
        System.out.println("It takes "+duration4+" nmls to remove [99999] in myArrList by romove(object).");

    }

}
