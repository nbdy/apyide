package io.eberlein.apyide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class CodeStyles {
    private List<LanguageStyle> styles;

    CodeStyles(){
        styles = new ArrayList<>();
    }

    CodeStyles(File directory){
        styles = new ArrayList<>();
        // todo
    }

    void add(LanguageStyle c){
        styles.add(c);
    }

    LanguageStyle get(String name){
        for(LanguageStyle s : styles){
            if(s.getName().equals(name)) return s;
        }
        return null;
    }

    String[] getNames(){
        List<String> r = new ArrayList<>();
        for(LanguageStyle c : styles) r.add(c.getName());
        return (String[]) r.toArray();
    }

    public List<LanguageStyle> getStyles() {
        return styles;
    }
}
