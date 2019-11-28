package io.eberlein.apyide.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.apyide.R;
import io.eberlein.apyide.Utils;

public class CodeStylesSettingsFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @OnClick(R.id.add)
    void add(){
        Utils.replaceFragment(R.id.nav_host_fragment, this, new CodeStyleSettingsFragment());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings_code_styles, container, false);
        ButterKnife.bind(this, v);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }
}
