package io.eberlein.apyide;

import java.io.File;
import java.util.List;

class ProjectConfiguration {
    private List<String> arguments;
    private File main;

    ProjectConfiguration(){}

    public List<String> getArguments() {
        return arguments;
    }
}
