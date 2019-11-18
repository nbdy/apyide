package io.eberlein.apyide;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private Long lastModified = 0L;
    private File path;
    private List<File> files;

    public Project(File path) {
        this.path = path;
        this.files = new ArrayList<>();
        load(path);
    }

    private void load(File directory){
        Log.d("Project.load", directory.getPath());
        for(File f : directory.listFiles()){
            if(f.lastModified() > lastModified) lastModified = f.lastModified();
            if(f.isDirectory()) load(f);
            else if(f.isFile()){
                Log.d(">", f.getName());
                files.add(f);
            }
        }
    }

    public Long getLastModified() {
        return lastModified;
    }

    public String getName(){
        return path.getName();
    }

    public File getPath() {
        return path;
    }

    public File has(String file){
        for(File f : files) {
            if(f.getName().equals(file)) return f;
        }
        return null;
    }

    public List<File> getFiles() {
        return files;
    }
}
