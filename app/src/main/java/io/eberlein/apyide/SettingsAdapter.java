package io.eberlein.apyide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {
    private Context ctx;
    private Settings settings;
    private Fragment currentFragment;

    public SettingsAdapter(Context ctx, Fragment currentFragment){
        this.ctx = ctx;
        this.currentFragment = currentFragment;
        this.settings = new Settings();
    }

    public SettingsAdapter(Context ctx, Fragment currentFragment, Settings settings){
        this.ctx = ctx;
        this.currentFragment = currentFragment;
        this.settings = settings;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Setting setting;

        @BindView(R.id.tv_left_up) TextView tv_left_up;
        @BindView(R.id.tv_left_center) TextView tv_left_center;
        @BindView(R.id.tv_left_down) TextView tv_left_down;
        @BindView(R.id.tv_right_up) TextView tv_right_up;
        @BindView(R.id.tv_right_center) TextView tv_right_center;
        @BindView(R.id.tv_right_down) TextView tv_right_down;

        @BindView(R.id.btn_right) Button btn_right;

        @OnClick
        void onClick(View v){
            setting.open(currentFragment);
        }

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        void setSetting(Setting s){
            setting = s;
            tv_left_center.setText(s.getName());
            tv_left_center.setTextSize(16);
        }
    }

    @NonNull
    @Override
    public SettingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsAdapter.ViewHolder holder, int position) {
        holder.setSetting(settings.getSetting(position));
    }

    @Override
    public int getItemCount() {
        return settings.getSize();
    }
}
