package io.eberlein.apyide.codestyles;

import java.util.ArrayList;
import java.util.List;

import io.eberlein.apyide.R;

public class CodeColor {
    private int color;
    private List<String> words;

    public CodeColor(){
        color = R.color.white;
        words = new ArrayList<>();
    }

    public CodeColor(int color){
        this.color = color;
        words = new ArrayList<>();
    }

    public CodeColor(int color, List<String> words){
        this.color = color;
        this.words = words;
    }

    public void addWord(String word){
        if(!words.contains(word)) words.add(word);
    }

    public void removeWord(String word){
        words.remove(word);
    }

    public int getColor() {
        return color;
    }

    public List<String> getWords() {
        return words;
    }
}
