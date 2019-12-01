package io.eberlein.apyide.codestyles;

import java.util.UUID;

import io.eberlein.apyide.R;

public class CodeColor {
    private String key;
    private int color;
    private String word;

    public CodeColor(){
        key = UUID.randomUUID().toString();
        color = R.color.white;
    }

    public CodeColor(int color){
        key = UUID.randomUUID().toString();
        this.color = color;
    }

    public CodeColor(int color, String word){
        key = UUID.randomUUID().toString();
        this.color = color;
        this.word = word;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getColor() {
        return color;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
