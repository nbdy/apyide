package io.eberlein.apyide.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.apyide.R;
import io.eberlein.apyide.adapters.CodeColorAdapter;
import io.eberlein.apyide.codestyles.CodeStyle;

public class CodeStyleSettingsFragment extends Fragment {
    private CodeStyle style;
    private CodeColorAdapter adapter;

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.code)
    EditText code;

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @OnClick(R.id.add)
    void add(){
        // todo dialog
    }

    public CodeStyleSettingsFragment(CodeStyle style){
        this.style = style;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings_code_style, container, false);
        ButterKnife.bind(this ,v);
        adapter = new CodeColorAdapter(getContext(), this, style.getCodeColors());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }
}
