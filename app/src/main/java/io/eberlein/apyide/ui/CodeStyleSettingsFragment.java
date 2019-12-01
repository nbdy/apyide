package io.eberlein.apyide.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.larswerkman.holocolorpicker.ColorPicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.apyide.R;
import io.eberlein.apyide.adapters.CodeColorAdapter;
import io.eberlein.apyide.codestyles.CodeColor;
import io.eberlein.apyide.codestyles.CodeStyle;
import io.eberlein.apyide.events.CodeColorEditEvent;

public class CodeStyleSettingsFragment extends Fragment {
    private CodeStyle style;
    private CodeColorAdapter adapter;

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.code)
    EditText code;

    @BindView(R.id.recycler)
    RecyclerView recycler;

    private void showColorPickerDialog(final CodeColor c){
        AlertDialog.Builder b = new AlertDialog.Builder(getContext());
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_code_color, null, false);
        ColorPicker picker = ((ColorPicker) v.findViewById(R.id.picker));
        EditText code = ((EditText) v.findViewById(R.id.code));
        picker.setColor(c.getColor());
        code.setText(c.getWord());
        b.setView(v);
        b.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        b.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                c.setWord(code.getText().toString());
                c.setColor(picker.getColor());
                style.addCodeColor(c);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        b.show();
    }

    @OnClick(R.id.add)
    void add(){
        showColorPickerDialog(new CodeColor());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEditEvent(CodeColorEditEvent e){
        showColorPickerDialog(e.getCodeColor());
    }

    public CodeStyleSettingsFragment(CodeStyle style){
        this.style = style;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings_code_style, container, false);
        ButterKnife.bind(this, v);
        name.setText(style.getName());
        code.setText(style.getSampleCode());
        adapter = new CodeColorAdapter(getContext(), style.getCodeColors());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        return v;
    }

    @Override
    public void onDestroy() {
        style.setName(name.getText().toString());
        style.save();
        super.onDestroy();
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
