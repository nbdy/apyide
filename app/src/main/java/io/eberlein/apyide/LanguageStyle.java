package io.eberlein.apyide;

import android.graphics.Color;
import android.widget.EditText;

import com.jmpergar.awesometext.AwesomeTextHandler;

import java.util.ArrayList;
import java.util.List;

class Colors {
    public static final int ORANGE = Color.rgb(240, 143, 58);
    public static final int WHITE = Color.WHITE;
    public static final int YELLOW = Color.YELLOW;
    public static final int BLUE = Color.BLUE;
    public static final int GREEN = Color.GREEN;
    public static final int PURPLE = Color.rgb(77, 27, 128);
}


public class LanguageStyle {
    protected  String name;
    List<String> ORANGE;
    List<String> WHITE;
    List<String> YELLOW;
    List<String> BLUE;
    List<String> GREEN;
    List<String> PURPLE;

    LanguageStyle(){
        name = "default";
        ORANGE = new ArrayList<>();
        WHITE = new ArrayList<>();
        YELLOW = new ArrayList<>();
        BLUE = new ArrayList<>();
        GREEN = new ArrayList<>();
        PURPLE = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void compile(EditText et){
        AwesomeTextHandler ath = new AwesomeTextHandler();
        for(String p : ORANGE) ath.addViewSpanRenderer(p, new CodeStyle(p, Colors.ORANGE, false));
        for(String p : WHITE) ath.addViewSpanRenderer(p, new CodeStyle(p, Colors.WHITE, false));
        for(String p : YELLOW) ath.addViewSpanRenderer(p, new CodeStyle(p, Colors.YELLOW, false));
        for(String p : BLUE) ath.addViewSpanRenderer(p, new CodeStyle(p, Colors.BLUE, false));
        for(String p : GREEN) ath.addViewSpanRenderer(p, new CodeStyle(p, Colors.GREEN, false));
        for(String p : PURPLE) ath.addViewSpanRenderer(p, new CodeStyle(p, Colors.PURPLE, false));
        ath.setView(et);
    }
}


class PythonDarkula extends LanguageStyle {
    PythonDarkula(){
        super();
        name = "pyDarkula";
        WHITE.add("\\w");
        ORANGE.add("if");
        ORANGE.add("import");
        ORANGE.add("from");
        YELLOW.add("\\\".*\\\"");
        GREEN.add("'.*'");
        PURPLE.add("print");
        BLUE.add("\\d*");
    }
}

