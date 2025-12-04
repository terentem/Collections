package org.example.collectionTest;
import org.example.model.TreeMapNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class MyTreeMap {

    //Сканування всіх папок в rootDir = "root_tree"

    public static void scanRootDir(String stringRootPath)
    {
        Path startFrom = Path.of(stringRootPath);
        TreeMap<Path, TreeMapNode> trmap = new TreeMap<>(Comparator
                .comparingInt((Path p) -> p.getNameCount())  // глибина
                .thenComparing(Path::toString)   );
        List<Path> allPathsList;
        System.out.println("scan startFrom= "+startFrom);
        try  (Stream<Path> allPaths = Files.walk(startFrom)){
            allPathsList=allPaths.toList();
            for(Path p:allPathsList)
                {File file=p.toFile();
                TreeMapNode node=new TreeMapNode(file);
                trmap.put(p, node);
                //System.out.println(p);
                }
            System.out.println("***********************************");
            System.out.println("Пошук файла find.txt");
            int count=0;
            for(Path key: trmap.keySet())
            {
                if(key.getFileName().toString().equals("find_me.txt")){
                   count+=1;
                   System.out.println(key);
                };
            }
            System.out.println("***********************************");
            System.out.println("Кількість знайдених файлів = "+count);
            System.out.println("***********************************");
            System.out.println("Відображення значень з myTreeMap: ");
            for(Map.Entry<Path, TreeMapNode> entry : trmap.entrySet()){
                Path key=entry.getKey();
                TreeMapNode value= entry.getValue();
                //System.out.println("Path= "+key+" | Type (file or dir= "+ value.type+ " | Depth= "+ value.depth);
                System.out.println("key= "+key);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main (String args[]){
        File[] roots = File.listRoots();
        System.out.println("***********************************");
        System.out.println("Directories: ");
        for(File root:roots){
            System.out.println(root);
        }
        System.out.println("***********************************");

        FileStore store = null;
        try {
            store = Files.getFileStore(Paths.get("C:\\"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String fs = store.type();
        System.out.println("fs: "+fs);
        System.out.println("***********************************");
        String stringRootPath="D:\\Education\\java\\root_tree";
        scanRootDir(stringRootPath);











    }

}
