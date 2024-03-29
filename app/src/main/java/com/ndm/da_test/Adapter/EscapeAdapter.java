package com.ndm.da_test.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ndm.da_test.Entities.Escape;
import com.ndm.da_test.Interface.IClickItemEscapeListener;
import com.ndm.da_test.R;


import java.util.List;

public class EscapeAdapter extends RecyclerView.Adapter<EscapeAdapter.EscapeViewHolder> {

    private IClickItemEscapeListener iClickItemEscapeListener;
    private List<Escape> mEscapeList;

    public EscapeAdapter(List<Escape> escapeList, IClickItemEscapeListener listener) {
        mEscapeList = escapeList;
        iClickItemEscapeListener = listener;
    }

    @NonNull
    @Override
    public EscapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_escape_skill, parent, false);
        return new EscapeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EscapeViewHolder holder, int position) {
        Escape escape = mEscapeList.get(position);
        if(mEscapeList == null)
        {
            return;
        }
        try {
            int tvIdValue = position + 1;
            holder.tvId.setText(String.valueOf(tvIdValue));
            holder.tvNameAction.setText(escape.getName());
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iClickItemEscapeListener.onItemClick(escape);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if(mEscapeList != null)
        {
            return mEscapeList.size();
        }
        return 0;
    }

    public class EscapeViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvNameAction;
        LinearLayout layout;

        public EscapeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvNameAction = itemView.findViewById(R.id.tv_name_action);
            layout = itemView.findViewById(R.id.layout_item);
        }
    }
}

