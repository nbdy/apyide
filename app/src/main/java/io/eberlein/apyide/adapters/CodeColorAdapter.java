package io.eberlein.apyide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.ButterKnife;
import io.eberlein.apyide.R;
import io.eberlein.apyide.codestyles.CodeColor;

public class CodeColorAdapter extends RecyclerView.Adapter<CodeColorAdapter.ViewHolder> {
    private Context ctx;
    private Fragment currentFragment;
    private List<CodeColor> codeColors;

    public CodeColorAdapter(Context ctx, Fragment currentFragment, List<CodeColor> codeColors){
        this.ctx = ctx;
        this.currentFragment = currentFragment;
        this.codeColors = codeColors;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        void setCodeColor(CodeColor c){

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_code_color, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setCodeColor(codeColors.get(position));
    }

    @Override
    public int getItemCount() {
        return codeColors.size();
    }
}
