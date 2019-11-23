package io.eberlein.apyide;

import android.graphics.Color;
import android.widget.EditText;

import com.jaychang.st.SimpleText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class CodeColor {
    private int color;
    private List<String> words;

    CodeColor(){
        color = R.color.white;
        words = new ArrayList<>();
    }

    CodeColor(int color){
        this.color = color;
        words = new ArrayList<>();
    }

    CodeColor(int color, List<String> words){
        this.color = color;
        this.words = words;
    }

    void addWord(String word){
        if(!words.contains(word)) words.add(word);
    }

    void removeWord(String word){
        words.remove(word);
    }

    public int getColor() {
        return color;
    }

    public List<String> getWords() {
        return words;
    }
}


public class LanguageStyle {
    protected  String name;

    List<CodeColor> colors;

    LanguageStyle(){
        name = "default";
        colors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void compile(String s){

    }

    public void compile(EditText et){
        SimpleText st = SimpleText.from(et.getText());
        for(CodeColor c : colors){
            for(String s : c.getWords()) st.all(s).textColor(c.getColor());
        }
        et.setText(st);
    }
}


class PythonDarkula extends LanguageStyle {
    PythonDarkula(){
        super();
        name = "pyDarkula";
        colors.addAll(Arrays.asList(
                new CodeColor(R.color.white, Arrays.asList("\\w")),
                new CodeColor(R.color.orange, Arrays.asList("if", "import", "from")),
                new CodeColor(R.color.yellow, Arrays.asList("\\\".*\\\"")),
                new CodeColor(R.color.green, Arrays.asList("'.*'")),
                new CodeColor(R.color.purple, Arrays.asList("print")),
                new CodeColor(R.color.blue, Arrays.asList("\\d*"))
        ));
    }
}

