package io.eberlein.apyide.codestyles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class CodeStyles {
    private List<LanguageStyle> styles;

    public CodeStyles(){
        styles = new ArrayList<>();
    }

    public CodeStyles(File directory){
        styles = new ArrayList<>();
        // todo
    }

    public void add(LanguageStyle c){
        styles.add(c);
    }

    public LanguageStyle get(String name){
        for(LanguageStyle s : styles){
            if(s.getName().equals(name)) return s;
        }
        return null;
    }

    public String[] getNames(){
        List<String> r = new ArrayList<>();
        for(LanguageStyle c : styles) r.add(c.getName());
        return (String[]) r.toArray();
    }

    public List<LanguageStyle> getStyles() {
        return styles;
    }

    public int size(){
        return styles.size();
    }
}
