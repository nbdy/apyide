package io.eberlein.apyide;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Projects {
    private File path;
    private final List<Project> projects;

    public Projects(@NonNull File path){
        projects = new ArrayList<>();
        if(path.exists()) {
            this.path = path;
            loadDirectory();
        }
    }

    private void loadDirectory(){
        Log.d("Projects.loadDirectory", "loading " + path.getPath());
        for(File f : Objects.requireNonNull(path.listFiles())){
            Log.d("> ", f.getName());
            add(new Project(f));
        }
    }

    public Project getProject(String name){
        for(Project p : projects) {
            if(p.getName().equals(name)) return p;
        }
        return null;
    }

    public Project getProject(int id){
        return projects.get(id);
    }

    public int size(){
        return projects.size();
    }

    private boolean has(Project p){
        for(Project _p : projects){
            if(_p.getName().equals(p.getName())) return true;
        }
        return false;
    }

    public boolean add(Project p){
        if(!has(p)) {projects.add(p); return true;}
        return false;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void remove(Project project){
        projects.remove(project);
        project.delete();
    }
}
