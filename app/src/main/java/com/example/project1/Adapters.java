package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class Adapters extends RecyclerView.Adapter<Adapters.ViewHolder> {

    private Context context;
    private List<SitesListItem> itemList;
    private OnItemClickListener listener;

    // 🔥 Interface for item click
    public interface OnItemClickListener {
        void onItemClick(SitesListItem item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Adapters(Context context, List<SitesListItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SitesListItem item = itemList.get(position);

        // 🔄 Load slider images for each item
        List<SlideModel> slideModels = new ArrayList<>();
        for (int imageRes : item.getImageList()) {
            slideModels.add(new SlideModel(imageRes, "", ScaleTypes.FIT));
        }
        holder.imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        holder.locationIcon.setImageResource(item.getLocationIcon());
        holder.buildingIcon.setImageResource(item.getBuildingIcon());
        holder.measurementIcon.setImageResource(item.getMeasurementIcon());

        holder.projectName.setText(item.getProjectName());
        holder.address.setText(item.getAddress());
        holder.flatType.setText(item.getFlatType());
        holder.measurement.setText(item.getMeasurement());

        // ✅ Handle click
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageSlider imageSlider;
        ImageView locationIcon, buildingIcon, measurementIcon;
        TextView projectName, address, flatType, measurement;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSlider = itemView.findViewById(R.id.item_image);
            locationIcon = itemView.findViewById(R.id.location);
            buildingIcon = itemView.findViewById(R.id.site_A);
            measurementIcon = itemView.findViewById(R.id.area);
            projectName = itemView.findViewById(R.id.item_title);
            address = itemView.findViewById(R.id.sites_location);
            flatType = itemView.findViewById(R.id.sites_available);
            measurement = itemView.findViewById(R.id.sq_ft);
        }
    }
}
