package io.eberlein.apyide.codestyles;

import android.content.res.Resources;
import android.graphics.Color;
import android.text.Editable;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.List;

import io.eberlein.apyide.Static;
import io.paperdb.Paper;


public class CodeStyle {
    private String name;
    private String sampleCode;
    private List<CodeColor> colors;

    public CodeStyle(){
        name = "default";
        sampleCode = "from log import logging";
        colors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSampleCode(String sampleCode) {
        this.sampleCode = sampleCode;
    }

    public String getSampleCode() {
        return sampleCode;
    }

    public Editable compile(Resources res, Editable e){
        String t = e.toString();
        for(CodeColor c : colors){
            String s = c.getWord();
            for(int i = t.indexOf(s); i >= 0; i = t.indexOf(s, i + 1)){
                e.setSpan(
                        new ForegroundColorSpan(c.getColor()),
                        i, i + s.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return e;
    }

    public boolean addCodeColor(CodeColor codeColor){
        for(CodeColor c : colors){
            if(c.getWord().contains(codeColor.getWord()) || codeColor.getWord().contains(c.getWord())) return false;
        }
        colors.add(codeColor);
        return true;
    }

    public List<CodeColor> getCodeColors(){
        return colors;
    }

    public void save(){
        Paper.book(Static.BOOK_CODESTYLES).write(name, this);
    }
}


