package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapters extends RecyclerView.Adapter<Adapters.ViewHolder> {

    private Context context;
    private ArrayList<SitesListItem> itemList;

    public Adapters(Context context, ArrayList<SitesListItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mainImage;
        TextView title, address;

        public ViewHolder(View itemView) {
            super(itemView);
            mainImage = itemView.findViewById(R.id.item_image); // update to your image ID
            title = itemView.findViewById(R.id.item_title);
            address = itemView.findViewById(R.id.sites_location);
        }
    }

    @Override
    public Adapters.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false); // replace with your item layout
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapters.ViewHolder holder, int position) {
        SitesListItem item = itemList.get(position);

        holder.mainImage.setImageResource(item.getImageRes());
        holder.title.setText(item.getTitle());
        holder.address.setText(item.getAddress());

        holder.mainImage.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("siteData", item);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
