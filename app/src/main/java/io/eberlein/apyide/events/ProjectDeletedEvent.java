package io.eberlein.apyide.events;

import io.eberlein.apyide.Project;

public class ProjectDeletedEvent {
    private Project project;

    public ProjectDeletedEvent(Project project){
        this.project = project;
    }

    public Project getProject() {
        return project;
    }
}
