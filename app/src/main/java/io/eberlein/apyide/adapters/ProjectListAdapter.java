package io.eberlein.apyide.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import io.eberlein.apyide.Project;
import io.eberlein.apyide.Projects;
import io.eberlein.apyide.R;

public class ProjectListAdapter extends BaseExpandableListAdapter {

    private Context ctx;
    private Projects projects;

    public ProjectListAdapter(Context ctx){
        this.ctx = ctx;
    }

    public ProjectListAdapter(Context ctx, Projects projects){
        this.ctx = ctx;
        this.projects = projects;
    }

    public void add(Project p) {

    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String hv = (String) getGroup(groupPosition);
        if(convertView == null) convertView = LayoutInflater.from(ctx).inflate(R.layout.project_group, null);
        TextView lh = (TextView) convertView.findViewById(R.id.tv_lst_header);
        lh.setTypeface(null, Typeface.BOLD);
        lh.setText(hv);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String ct = (String) getChild(groupPosition, childPosition);
        if(convertView == null) convertView = LayoutInflater.from(ctx).inflate(R.layout.project_item, null);
        TextView tc = (TextView) convertView.findViewById(R.id.tv_lst_item);
        tc.setText(ct);
        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return projects.getProject(groupPosition).getFiles().get(childPosition);
    }

    @Override
    public int getGroupCount() {
        return projects.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return projects.getProject(groupPosition).getFiles().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return projects.getProject(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
