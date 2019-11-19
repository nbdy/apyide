package io.eberlein.apyide;

import android.content.Context;

import java.util.List;

public class ProjectRunConfiguration {
    private String main;
    private List<String> arguments;

    public String getMain() {
        return main;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void addArgument(String argument){
        this.arguments.add(argument);
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void run(Context ctx){

    }
}
