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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import io.eberlein.apyide.codestyles.CodeStyle;
import io.eberlein.apyide.codestyles.CodeStyles;
import io.eberlein.apyide.R;
import io.eberlein.apyide.events.CodeStyleDeletedEvent;
import io.eberlein.apyide.ui.CodeStyleSettingsFragment;

public class CodeStyleAdapter extends RecyclerView.Adapter<CodeStyleAdapter.ViewHolder> {
    private Context ctx;
    private CodeStyles codeStyles;
    private Fragment currentFragment;

    public CodeStyleAdapter(Context ctx, Fragment currentFragment, CodeStyles codeStyles){
        this.ctx = ctx;
        this.codeStyles = codeStyles;
        this.currentFragment = currentFragment;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private boolean onExtraMenuOpen = false;
        private CodeStyle style;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.delete)
        Button delete;

        @OnClick(R.id.delete)
        void deleteClicked(){
            EventBus.getDefault().post(new CodeStyleDeletedEvent(style));
            closeExtraMenu();
        }

        @OnClick
        void onClick(){
            FragmentUtils.replace(currentFragment, new CodeStyleSettingsFragment(style), true);
        }

        @OnLongClick
        void onLongClick(){
            if(onExtraMenuOpen) closeExtraMenu();
            else openExtraMenu();
        }

        void openExtraMenu(){
            onExtraMenuOpen = true;
            delete.setVisibility(View.VISIBLE);
        }

        void closeExtraMenu(){
            onExtraMenuOpen = false;
            delete.setVisibility(View.GONE);
        }

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        void setCodeStyle(CodeStyle c){
            style = c;
            name.setText(c.getName());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_code_style, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setCodeStyle(codeStyles.get(position));
    }

    @Override
    public int getItemCount() {
        return codeStyles.size();
    }
}
