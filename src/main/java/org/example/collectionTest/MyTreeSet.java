package org.example.collectionTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeSet;
import java.util.stream.Stream;

public class MyTreeSet {
    public static void main(String[] args){
        String startFrom="D:\\Education\\java\\root_tree";
        TreeSet<Path> trset=new TreeSet<>();
        try (
            Stream<Path> paths = Files.walk(Path.of(startFrom))){
            paths.forEach(p->trset.add(p));
            System.out.println("Size of myTreeSet= "+trset.size());
            System.out.println("***********************************");
            System.out.println("Keys from myTreeSet= ");
            for(Path key:trset){
                System.out.println(key);
            }
            System.out.println("***********************************");
            System.out.println("First from myTreeSet= "+trset.first());
            System.out.println("Last from myTreeSet= "+trset.last());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
