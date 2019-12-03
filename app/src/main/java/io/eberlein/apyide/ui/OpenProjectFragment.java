package io.eberlein.apyide.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.eberlein.apyide.Projects;
import io.eberlein.apyide.R;
import io.eberlein.apyide.adapters.ProjectsAdapter;
import io.eberlein.apyide.Utils;
import io.eberlein.apyide.events.ProjectDeletedEvent;

public class OpenProjectFragment extends Fragment {
    private Projects projects;
    private ProjectsAdapter adapter;

    @BindView(R.id.rv_projects) RecyclerView recycler;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_open_project, container, false);
        ButterKnife.bind(this, root);
        populateProjects();
        return root;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProjectDeletedEvent(ProjectDeletedEvent e){
        projects.remove(e.getProject()); // todo fix
        adapter.notifyDataSetChanged();
    }

    private void populateProjects(){
        Context ctx = getContext();
        projects = Utils.getProjects(ctx);
        adapter = new ProjectsAdapter(ctx, this, projects);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
