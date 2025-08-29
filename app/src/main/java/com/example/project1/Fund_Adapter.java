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

public class Fund_Adapter extends RecyclerView.Adapter<Fund_Adapter.ViewHolder> {

    private Context context;
    private List<Fund_model_class> fundList;

    public Fund_Adapter(Context context, List<Fund_model_class> fundList) {
        this.context = context;
        this.fundList = fundList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.paid_fund_d, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fund_model_class item = fundList.get(position);

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
            txtStatus = itemView.findViewById(R.id.paid);
            statusIcon = itemView.findViewById(R.id.paid_icon); // add id in XML if needed
            txtWing = itemView.findViewById(R.id.winga);
            txtFlat = itemView.findViewById(R.id.wingb);
        }
    }
}
