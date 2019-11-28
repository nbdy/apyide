package io.eberlein.apyide.codestyles;

import java.util.Arrays;

import io.eberlein.apyide.R;

public class PythonDarkula extends CodeStyle {
    public PythonDarkula(){
        super();
        name = "pyDarkula";
        colors.addAll(Arrays.asList(
                //new CodeColor(R.color.white, Arrays.asList("")),
                new CodeColor(R.color.orange, Arrays.asList(
                        "def", "in", "if", "import", "from", "class", "pass", "while", "True", "False",
                        "for", ",")),
                //new CodeColor(R.color.yellow, Arrays.asList("")),
                //new CodeColor(R.color.green, Arrays.asList("")),
                new CodeColor(R.color.purple, Arrays.asList(
                        "__init__", "self")),
                new CodeColor(R.color.blue, Arrays.asList(
                        "print", "object", "range", "isinstance", "issubclass", "list", "set",
                        "frozenset"))
        ));
    }
}
