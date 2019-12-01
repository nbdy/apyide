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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.eberlein.apyide.R;
import io.eberlein.apyide.adapters.ProjectsAdapter;
import io.eberlein.apyide.Utils;

public class OpenProjectFragment extends Fragment {
    @BindView(R.id.rv_projects) RecyclerView projects;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_open_project, container, false);
        ButterKnife.bind(this, root);
        populateProjects();
        return root;
    }

    private void populateProjects(){
        Context ctx = getContext();
        projects.setAdapter(new ProjectsAdapter(ctx, getFragmentManager(), Utils.getProjects(ctx)));
        projects.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
