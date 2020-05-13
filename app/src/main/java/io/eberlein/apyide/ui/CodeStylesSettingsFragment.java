package io.eberlein.apyide.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.FragmentUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.apyide.R;
import io.eberlein.apyide.adapters.CodeStyleAdapter;
import io.eberlein.apyide.codestyles.CodeStyle;
import io.eberlein.apyide.codestyles.CodeStyles;
import io.eberlein.apyide.events.CodeStyleDeletedEvent;

public class CodeStylesSettingsFragment extends Fragment {
    private CodeStyleAdapter adapter;
    private CodeStyles styles;

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @OnClick(R.id.add)
    void add(){
        FragmentUtils.replace(this, new CodeStyleSettingsFragment(new CodeStyle()), true);
    }

    @SuppressLint("LongLogTag")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStyleDeletedEvent(CodeStyleDeletedEvent e){
        Log.d("CodeStyleSettingsFragment.onStyleDeletedEvent", e.getStyle().getName());
        styles.remove(e.getStyle());
        adapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings_code_styles, container, false);
        ButterKnife.bind(this, v);
        styles = CodeStyles.load();
        adapter = new CodeStyleAdapter(getContext(), this, styles);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
