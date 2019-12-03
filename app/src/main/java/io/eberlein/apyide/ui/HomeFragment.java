package io.eberlein.apyide.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.apyide.R;
import io.eberlein.apyide.Static;
import io.eberlein.apyide.Termux;
import io.paperdb.Paper;

public class HomeFragment extends Fragment {
    @BindView(R.id.btn_install_python) Button installPython;

    @OnClick(R.id.btn_create_project)
    void createProjectClicked(){
        FragmentUtils.replace(this, new NewProjectFragment(), true);
    }

    @OnClick(R.id.btn_open_project)
    void openProjectClicked(){
        FragmentUtils.replace(this, new OpenProjectFragment(), true);
    }

    @OnClick(R.id.btn_install_python)
    void install_python(){
        Termux t = new Termux(getContext());
        // stuff is executed from the bottom up, idk why
        t.run("pkg", new String[]{"install", "-y", "python"});
        t.run("pkg", new String[]{"update", "-y"});
        Paper.book(Static.BOOK_SETTINGS).write("python", true);
        installPython.setVisibility(View.GONE);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        if(!Paper.book(Static.BOOK_SETTINGS).read("python", false)){
            installPython.setVisibility(View.GONE);
        }
        return root;
    }
}