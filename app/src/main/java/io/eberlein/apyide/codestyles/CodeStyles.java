package io.eberlein.apyide.codestyles;

import java.util.ArrayList;
import java.util.List;

import io.eberlein.apyide.Static;
import io.paperdb.Book;
import io.paperdb.Paper;


public class CodeStyles {
    private List<CodeStyle> styles;

    public CodeStyles(){
        styles = new ArrayList<>();
    }

    public static CodeStyles load(){
        Book b = Paper.book(Static.BOOK_CODESTYLES);
        CodeStyles cs = new CodeStyles();
        for(String k : b.getAllKeys()){
            cs.add(b.read(k));
        }
        return cs;
    }

    public void add(CodeStyle c){
        styles.add(c);
    }

    public CodeStyle get(String name){
        for(CodeStyle s : styles){
            if(s.getName().equals(name)) return s;
        }
        return null;
    }

    public void remove(CodeStyle codeStyle){
        styles.remove(codeStyle);
        Paper.book(Static.BOOK_CODESTYLES).delete(codeStyle.getName());
    }

    public CodeStyle get(int p){
        return styles.get(p);
    }

    public String[] getNames(){
        List<String> r = new ArrayList<>();
        for(CodeStyle c : styles) r.add(c.getName());
        return (String[]) r.toArray();
    }

    public List<CodeStyle> getStyles() {
        return styles;
    }

    public int size(){
        return styles.size();
    }
}
