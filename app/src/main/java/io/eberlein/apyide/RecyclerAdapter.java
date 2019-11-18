package io.eberlein.apyide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.apyide.ui.EditSourceFragment;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Projects projects;
    private Context ctx;
    private Fragment currentFragment;

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_left_up) TextView left_up;
        @BindView(R.id.tv_left_center) TextView left_center;
        @BindView(R.id.tv_left_down) TextView left_down;
        @BindView(R.id.tv_right_up) TextView right_up;
        @BindView(R.id.tv_right_center) TextView right_center;
        @BindView(R.id.tv_right_down) TextView right_down;

        @OnClick
        void onClick(View v){
            Utils.replaceFragment(R.id.nav_host_fragment, currentFragment, new EditSourceFragment(projects.getProject(getAdapterPosition())));
        }

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public RecyclerAdapter(Context ctx, Fragment currentFragment){
        this.ctx = ctx;
        this.currentFragment = currentFragment;
    }

    public RecyclerAdapter(Context ctx, Projects projects, Fragment currentFragment) {
        this.ctx = ctx;
        this.projects = projects;
        this.currentFragment = currentFragment;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        Project p = projects.getProject(position);
        holder.left_center.setText(p.getPath().getName());
        holder.left_center.setTextSize(16);
        holder.right_down.setText(p.getPath().getAbsolutePath());
        holder.right_up.setText(new Date(p.getLastModified()).toString());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void add(Project p){
        if(projects.add(p)) notifyItemChanged(projects.size());
    }

    public void add(Projects ps){
        for(Project p : ps.getProjects()){
            if(projects.add(p)) notifyItemChanged(projects.size());
        }
    }
}
