package io.eberlein.apyide;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private Long lastModified = 0L;
    private File path;
    private String name;
    private List<File> files;

    private ProjectRunConfiguration prc;

    public Project(File path) {
        this.path = path;
        this.files = new ArrayList<>();
        name = path.getName();
        load(path);
        loadRunConfig(path);
    }

    public void load(File directory){
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

    private void loadRunConfig(File path){
        // todo

    }

    public Long getLastModified() {
        return lastModified;
    }

    public String getName(){
        return path.getName();
    }

    public File getMain(Context ctx){
        return Utils.getAPyIDEProjectPath(ctx, path.getName() + "/" + path.getName() + ".py");
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

    public void delete(){
        if(!path.delete()) Log.e("Project.delete;" + name, "could not delete project directory");
        else Log.d("Project.delete;" + name, "deleted directory");
    }
}
