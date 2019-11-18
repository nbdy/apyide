package io.eberlein.apyide.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.apyide.Project;
import io.eberlein.apyide.R;
import io.eberlein.apyide.Utils;

public class NewProjectFragment extends Fragment {
    @BindView(R.id.et_project_name) EditText projectName;

    @OnClick(R.id.btn_create_project)
    void btnCreateProjectClicked(){
        if(Utils.createProject(getContext(), projectName.getText().toString())) {
            // todo attach to navbar
            // open editfragment on main.py
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_project, container, false);
        ButterKnife.bind(this, root);
        return root;
    }
}
