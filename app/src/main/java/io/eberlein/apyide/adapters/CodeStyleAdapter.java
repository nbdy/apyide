package io.eberlein.apyide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;
import io.eberlein.apyide.codestyles.CodeStyles;
import io.eberlein.apyide.R;

public class CodeStyleAdapter extends RecyclerView.Adapter<CodeStyleAdapter.ViewHolder> {
    private Context ctx;
    private Fragment currentFragment;
    private CodeStyles codeStyles;

    public CodeStyleAdapter(Context ctx, Fragment currentFragment){
        this.ctx = ctx;
        this.currentFragment = currentFragment;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        void setCodeStyle(){

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_code_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return codeStyles.size();
    }
}
