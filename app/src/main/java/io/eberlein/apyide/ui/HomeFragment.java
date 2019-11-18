package io.eberlein.apyide.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.apyide.R;
import io.eberlein.apyide.Termux;
import io.eberlein.apyide.Utils;

public class HomeFragment extends Fragment {

    @OnClick(R.id.btn_create_project)
    void createProjectClicked(){
        Utils.replaceFragment(R.id.nav_host_fragment, this, new NewProjectFragment());
    }

    @OnClick(R.id.btn_open_project)
    void openProjectClicked(){
        Utils.replaceFragment(R.id.nav_host_fragment, this, new OpenProjectFragment());
    }

    @OnClick(R.id.btn_install_python)
    void install_python(){
        Termux t = new Termux(getContext());
        // stuff is executed from the bottom up, idk why
        t.run("pkg", new String[]{"install", "-y", "python"});
        t.run("pkg", new String[]{"update", "-y"});
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        return root;
    }
}