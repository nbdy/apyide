package io.eberlein.apyide.codestyles;

import android.content.res.Resources;
import android.text.Editable;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.List;

import io.eberlein.apyide.Static;
import io.paperdb.Paper;


public class CodeStyle {
    protected  String name;

    protected List<CodeColor> colors;

    public CodeStyle(){
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

    public List<CodeColor> getCodeColors(){
        return colors;
    }

    public void save(){
        Paper.book(Static.BOOK_CODESTYLES).write(name, this);
    }
}


