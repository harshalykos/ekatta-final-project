package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Unpaid_Adapter extends RecyclerView.Adapter<Unpaid_Adapter.ViewHolder> {

    private Context context;
    private List<Unpaid_model> fundList;

    public Unpaid_Adapter(Context context, List<Unpaid_model> fundList) {
        this.context = context;
        this.fundList = fundList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.unpaid_funds_d, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Unpaid_model item = fundList.get(position);

        holder.imgProfile.setImageResource(item.getProfileImage());
        holder.txtName.setText(item.getName());
        holder.txtDetails.setText(item.getDetails());
        holder.dept.setText(item.getDept());
        holder.txtWing.setText(item.getWing());
        holder.txtFlat.setText(item.getFlat());
        holder.statusIcon.setImageResource(item.getStatusIcon());
        holder.txtStatus.setText(item.getStatusText());
    }

    @Override
    public int getItemCount() {
        return fundList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProfile, statusIcon;
        TextView txtName, txtDetails, dept, txtWing, txtFlat, txtStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProfile = itemView.findViewById(R.id.imgProfile);
            txtName = itemView.findViewById(R.id.txtName);
            txtDetails = itemView.findViewById(R.id.txtDetails);
            dept = itemView.findViewById(R.id.dept);
            txtStatus = itemView.findViewById(R.id.unpaid);
            statusIcon = itemView.findViewById(R.id.unpaid_icon);
            txtWing = itemView.findViewById(R.id.winga);
            txtFlat = itemView.findViewById(R.id.wingb);
        }
    }
}
