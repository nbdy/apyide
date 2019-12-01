package io.eberlein.apyide.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.KeyboardUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.apyide.codestyles.CodeStyle;
import io.eberlein.apyide.Project;
import io.eberlein.apyide.R;
import io.eberlein.apyide.Termux;
import io.eberlein.apyide.Utils;
import io.eberlein.apyide.codestyles.CodeStyles;

public class EditSourceFragment extends Fragment {
    private CodeStyle codeStyle;
    private Project project;

    @BindView(R.id.et_source) EditText source;

    @OnClick(R.id.btn_run)
    void btnRunClicked(){
        List<String> args = new ArrayList<>();
        args.add(project.getMain(getContext()).getPath());
        // todo add extra arguments
        Termux.run(getContext(), "python3", (String[]) args.toArray());
    }

    public EditSourceFragment(Project project){
        Log.d("EditSourceFragment", "init");
        this.project = project;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String lt = "EditSourceFragment.onCreateView";
        Log.d(lt, "init");
        View root = inflater.inflate(R.layout.fragment_edit_source, container, false);
        Log.d(lt, "inflated");
        ButterKnife.bind(this, root);
        Log.d(lt, "bound");
        source.setText(Utils.readFile(project.getMain(getContext())));
        Log.d(lt, "read main");
        codeStyle = CodeStyles.load().getStyles().get(0); // todo make choose able / get via shared preferences
        Log.d(lt, "got language style");
        source.setText(codeStyle.compile(source.getText()));
        Log.d(lt, "set language style");
        source.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                codeStyle.compile(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroy() {
        KeyboardUtils.hideSoftInput(getActivity());
        super.onDestroy();
    }
}
