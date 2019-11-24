package io.eberlein.apyide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

import androidx.core.content.res.ResourcesCompat;

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

    public Editable compile(Resources res, Editable e){
        String t = e.toString();
        for(CodeColor c : colors){
            for(String s : c.getWords()){
                for(int i = t.indexOf(s); i >= 0; i = t.indexOf(s, i + 1)){
                    e.setSpan(
                            new ForegroundColorSpan(ResourcesCompat.getColor(res, c.getColor(), null)),
                            i, i + s.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        return e;
    }
}


class PythonDarkula extends LanguageStyle {
    PythonDarkula(){
        super();
        name = "pyDarkula";
        colors.addAll(Arrays.asList(
                new CodeColor(R.color.white, Arrays.asList("")),
                new CodeColor(R.color.orange, Arrays.asList(
                        "def", "if", "import", "from", "class", "pass", "while", "True", "False",
                        "for", "in", ",")),
                new CodeColor(R.color.yellow, Arrays.asList("")),
                new CodeColor(R.color.green, Arrays.asList("")),
                new CodeColor(R.color.purple, Arrays.asList(
                        "__init__", "self")),
                new CodeColor(R.color.blue, Arrays.asList(
                        "print", "object", "range", "isinstance", "issubclass", "list", "set",
                        "frozenset"))
        ));
    }
}

