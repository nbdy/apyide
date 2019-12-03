package io.eberlein.apyide.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.apyide.Project;
import io.eberlein.apyide.R;
import io.eberlein.apyide.Utils;

class NewProjectFragment extends Fragment {
    @BindView(R.id.et_project_name) EditText projectName;

    @OnClick(R.id.btn_create_project)
    void btnCreateProjectClicked(){
        String pn = projectName.getText().toString();
        if(pn.isEmpty()){
            Toast.makeText(getContext(), "project name is needed", Toast.LENGTH_LONG).show();
            return;
        }
        Project u = Utils.getCreateProject(getContext(), projectName.getText().toString());
        if(u != null) {
            FragmentUtils.replace(this, new EditSourceFragment(u), true);
            // todo attach to navbar
        } else {
            Toast.makeText(getContext(), "can not open / create '" + projectName.getText().toString() + "'", Toast.LENGTH_LONG).show();
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_project, container, false);
        ButterKnife.bind(this, root);
        return root;
    }
}
