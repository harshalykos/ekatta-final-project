package com.example.project1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private Context context;
    private List<CardItem> cardList;

    public CardAdapter(Context context, List<CardItem> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_status_list, parent, false); // Your item layout
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        CardItem item = cardList.get(position);

        holder.dateTextView.setText("Date : " + item.getDate());
        holder.serviceNoTextView.setText("Service No. : " + item.getServiceNo());
        holder.serviceCategoryTextView.setText("Service Category : " + item.getServiceCategory());
        holder.statusTextView.setText(item.getStatus());
        holder.descriptionTextView.setText(item.getDescription());
        holder.statusIconImageView.setImageResource(item.getStatusIconResId());
        holder.imageView.setImageResource(item.getImageResId());

        // Set card color based on status
        String status = item.getStatus().toLowerCase(); // normalize for case-insensitive matching
        int color;

        if (status.contains("in-process")) {
            color = ContextCompat.getColor(context, R.color.blue); // define color in colors.xml
        } else if (status.contains("completed")) {
            color = ContextCompat.getColor(context, R.color.green);
        } else if (status.contains("pending")) {
            color = ContextCompat.getColor(context, R.color.red);
        } else {
            color = Color.BLACK;
        }

        holder.cardView.setCardBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        TextView dateTextView, serviceNoTextView, serviceCategoryTextView, statusTextView, descriptionTextView;
        ImageView statusIconImageView, imageView;
        CardView cardView;

        public CardViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.card_date);
            serviceNoTextView = itemView.findViewById(R.id.card_service_no);
            serviceCategoryTextView = itemView.findViewById(R.id.card_service_category);
            statusTextView = itemView.findViewById(R.id.ststus); // updated status ID
            descriptionTextView = itemView.findViewById(R.id.description);
            statusIconImageView = itemView.findViewById(R.id.img);
            imageView = itemView.findViewById(R.id.img);

            cardView = itemView.findViewById(R.id.card_status); // Make sure your outer CardView has this ID
        }
    }
}
