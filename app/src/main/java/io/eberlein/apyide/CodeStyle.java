package io.eberlein.apyide;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jmpergar.awesometext.AwesomeTextHandler;


public class CodeStyle implements AwesomeTextHandler.ViewSpanRenderer {
    private int color;
    private boolean bold;
    private String pattern;

    CodeStyle(String pattern, int color, boolean bold){
        this.color = color;
        this.bold = bold;
        this.pattern = pattern;
    }

    @Override
    public View getView(String text, Context context) {
        TextView tv = new TextView(context);
        Log.d("CodeStyleRenderer.getView", pattern + " > " + text);
        if(text.isEmpty()) {
            tv.setText(text);
            tv.setWidth(64);
            tv.setHeight(64);
        } else {
            tv.setText(text.substring(1));
            tv.setTextColor(color);
            if(this.bold) tv.setTypeface(null, Typeface.BOLD);
        }
        tv.setTextSize(42);
        return tv;
    }

    public String getPattern() {
        return pattern;
    }

    public int getColor() {
        return color;
    }
}