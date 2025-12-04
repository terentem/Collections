package org.example.model;

import java.io.File;
import java.nio.file.Path;

public class TreeMapNode {
    public File fileObj;
    public int depth;
    public String type;


    public TreeMapNode (File fileObj){
        this.fileObj=fileObj;
        depth=calculateDepth(fileObj);
        this.type=fileObj.isFile()?"file":"dir";}


    private int calculateDepth(File fileObj){

        return fileObj.toPath().getNameCount();
    }


}





