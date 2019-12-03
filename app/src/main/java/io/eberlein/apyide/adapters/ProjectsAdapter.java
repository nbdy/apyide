package io.eberlein.apyide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.FragmentUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import io.eberlein.apyide.Project;
import io.eberlein.apyide.Projects;
import io.eberlein.apyide.R;
import io.eberlein.apyide.events.ProjectDeletedEvent;
import io.eberlein.apyide.ui.EditSourceFragment;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ViewHolder> {
    private final Projects projects;
    private final Context ctx;
    private final Fragment currentFragment;

    class ViewHolder extends RecyclerView.ViewHolder {
        private Project project;
        private Boolean extraMenuOpen = false;

        @BindView(R.id.tv_left_up) TextView tv_left_up;
        @BindView(R.id.tv_left_center) TextView tv_left_center;
        @BindView(R.id.tv_left_down) TextView tv_left_down;
        @BindView(R.id.tv_right_up) TextView tv_right_up;
        @BindView(R.id.tv_right_center) TextView tv_right_center;
        @BindView(R.id.tv_right_down) TextView tv_right_down;

        @BindView(R.id.btn_right) Button btn_right;

        @OnClick(R.id.btn_right)
        void deleteClicked(){
            EventBus.getDefault().post(new ProjectDeletedEvent(project));
        }

        @OnClick
        void onClick(){
            if(extraMenuOpen) closeExtraMenu();
            else FragmentUtils.replace(currentFragment, new EditSourceFragment(projects.getProject(getAdapterPosition())), true);
        }

        @OnLongClick
        void onLongClick(View v){
            if(extraMenuOpen) closeExtraMenu();
            else openExtraMenu(v);
        }

        void closeExtraMenu(){
            extraMenuOpen = false;
            tv_right_up.setText(new Date(project.getLastModified()).toString());
            tv_right_down.setText(project.getPath().toString());
            btn_right.setVisibility(View.GONE);
        }

        void openExtraMenu(View v){
            extraMenuOpen = true;
            tv_right_up.setText("");
            tv_right_center.setText("");
            tv_right_down.setText("");
            btn_right.setVisibility(View.VISIBLE);
            btn_right.setText(ctx.getText(R.string.delete));
        }

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        void setProject(Project p){
            project = p;
            tv_left_center.setText(p.getPath().getName());
            tv_left_center.setTextSize(16);
            tv_right_down.setText(p.getPath().getAbsolutePath());
            tv_right_up.setText(new Date(p.getLastModified()).toString());
        }
    }

    public ProjectsAdapter(Context ctx, Fragment currentFragment, Projects projects) {
        this.ctx = ctx;
        this.projects = projects;
        this.currentFragment = currentFragment;
    }

    @NonNull
    @Override
    public ProjectsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsAdapter.ViewHolder holder, int position) {
        holder.setProject(projects.getProject(position));
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
