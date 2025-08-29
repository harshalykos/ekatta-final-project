package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private final List<HistoryItem> historyList;

    public HistoryAdapter(List<HistoryItem> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_d, parent, false);
        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryItem item = historyList.get(position);
        holder.purpose.setText(item.getPurpose());
        holder.date.setText(item.getDate());
        holder.daysAgo.setText(item.getDaysAgo());
        holder.amount.setText(item.getAmount());
        holder.icon.setImageResource(item.getIconResId());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView purpose, date, daysAgo, amount;
        ImageView icon;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            purpose = itemView.findViewById(R.id.tvFundsUsedFor);
            date = itemView.findViewById(R.id.tvDate);
            daysAgo = itemView.findViewById(R.id.tvAgo);
            amount = itemView.findViewById(R.id.textRs);
            icon = itemView.findViewById(R.id.ivIcon);
        }
    }
}
