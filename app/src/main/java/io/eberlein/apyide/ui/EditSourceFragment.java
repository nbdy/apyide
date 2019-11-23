package io.eberlein.apyide.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.eberlein.apyide.CodeStyles;
import io.eberlein.apyide.Project;
import io.eberlein.apyide.R;
import io.eberlein.apyide.Termux;
import io.eberlein.apyide.Utils;

public class EditSourceFragment extends Fragment {

    private Project project;

    @BindView(R.id.et_source) EditText source;

    @OnClick(R.id.btn_run)
    void btnRunClicked(){

        List<String> args = new ArrayList<>();
        args.add(project.getMain(getContext()).getPath()); // todo make variable
        // todo add extra arguments
        Termux.run(getContext(), "python3", (String[]) args.toArray());
    }

    @OnTextChanged(value = R.id.et_source, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void sourceCodeChanged(){

    }

    public EditSourceFragment(Project project){
        this.project = project;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_edit_source, container, false);
        ButterKnife.bind(this, root);
        source.setText(Utils.readFile(project.has("main.py")));
        CodeStyles cs = Utils.getStyles(getContext());
        Log.d("EditSourceFragment.onCreateView", String.valueOf(cs.getStyles().size()));
        Log.d("EditSourceFragment.onCreateView", JSON.toJSONString(cs.getStyles().get(0)));
        cs.getStyles().get(0).compile(source); // todo make choose able
        return root;
    }
}
