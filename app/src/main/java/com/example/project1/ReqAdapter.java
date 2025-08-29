package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReqAdapter extends RecyclerView.Adapter<ReqAdapter.ReqViewHolder> {

    private List<ReqModel> reqList;

    public ReqAdapter(List<ReqModel> reqList) {
        this.reqList = reqList;
    }

    @NonNull
    @Override
    public ReqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.req_d, parent, false);
        return new ReqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReqViewHolder holder, int position) {
        ReqModel item = reqList.get(position);
        holder.purpose.setText(item.getPurpose());
        holder.amount.setText(item.getAmount());
        holder.date.setText(item.getDate());
        holder.daysAgo.setText(item.getDaysAgo());
        holder.statusIcon.setImageResource(item.getStatusIcon());
    }

    @Override
    public int getItemCount() {
        return reqList.size();
    }

    public static class ReqViewHolder extends RecyclerView.ViewHolder {
        TextView purpose, amount, date, daysAgo;
        ImageView statusIcon;

        public ReqViewHolder(@NonNull View itemView) {
            super(itemView);
            purpose = itemView.findViewById(R.id.pp);
            amount = itemView.findViewById(R.id.req_history);
            date = itemView.findViewById(R.id.req_date);
            daysAgo = itemView.findViewById(R.id.req_ago);
            statusIcon = itemView.findViewById(R.id.check);
        }
    }
}
