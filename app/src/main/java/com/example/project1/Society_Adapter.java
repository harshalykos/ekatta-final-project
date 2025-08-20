package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Society_Adapter extends RecyclerView.Adapter<Society_Adapter.Viewholder> {

    Context context;
    ArrayList<Society_list_item> list1;

    public Society_Adapter(Context context, ArrayList<Society_list_item> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.society_item_list, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Society_list_item item = list1.get(position);
        holder.imagedp.setImageResource(item.getSocietyUserDp());
        holder.s_user.setText(item.getSocietyUsername());
        holder.s_phone.setText(item.getSocietyMobile());
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        ImageView imagedp;
        TextView s_user, s_phone;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imagedp = itemView.findViewById(R.id.society_user_dp);
            s_user = itemView.findViewById(R.id.society_username);
            s_phone = itemView.findViewById(R.id.society_userphone);
        }
    }
}
