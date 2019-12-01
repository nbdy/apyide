package io.eberlein.apyide.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import io.eberlein.apyide.R;
import io.eberlein.apyide.Utils;
import io.eberlein.apyide.codestyles.CodeColor;
import io.eberlein.apyide.events.CodeColorEditEvent;

public class CodeColorAdapter extends RecyclerView.Adapter<CodeColorAdapter.ViewHolder> {
    private Context ctx;
    private List<CodeColor> codeColors;

    public CodeColorAdapter(Context ctx, List<CodeColor> codeColors){
        this.ctx = ctx;
        this.codeColors = codeColors;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private boolean onExtraMenuOpen = false;
        private CodeColor codeColor;

        @BindView(R.id.code)
        TextView code;

        @BindView(R.id.duplicate)
        Button duplicate;

        @OnClick(R.id.duplicate)
        void onDuplicateClicked(){
            CodeColor c = new CodeColor();
            c.setColor(codeColor.getColor());
            c.setWord(codeColor.getWord());
            codeColors.add(c);
            notifyDataSetChanged();
            closeExtraMenu();
        }

        @OnClick
        void onClick(){
            if(onExtraMenuOpen) closeExtraMenu();
            EventBus.getDefault().post(new CodeColorEditEvent(codeColor));
        }

        @OnLongClick
        void onLongClick(){
            if(onExtraMenuOpen) closeExtraMenu();
            else openExtraMenu();
        }

        void openExtraMenu(){
            onExtraMenuOpen = true;
            duplicate.setVisibility(View.VISIBLE);
        }

        void closeExtraMenu(){
            onExtraMenuOpen = false;
            duplicate.setVisibility(View.GONE);
        }

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        void setCodeColor(CodeColor c){
            codeColor = c;
            code.setText(c.getWord());
            code.setTextColor(c.getColor());
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
