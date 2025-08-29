package com.example.project1;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SitesListItem implements Parcelable, Serializable {
    private List<Integer> imageList;  // Multiple images for slider
    private int locationIcon, buildingIcon, measurementIcon;
    private String title, address, type, size;

    public SitesListItem(List<Integer> imageList, int locationIcon, int buildingIcon,
                         int measurementIcon, String title, String address,
                         String type, String size) {
        this.imageList = imageList;
        this.locationIcon = locationIcon;
        this.buildingIcon = buildingIcon;
        this.measurementIcon = measurementIcon;
        this.title = title;
        this.address = address;
        this.type = type;
        this.size = size;
    }

    // ✅ Getters
    public List<Integer> getImageList() { return imageList; }
    public int getLocationIcon() { return locationIcon; }
    public int getBuildingIcon() { return buildingIcon; }
    public int getMeasurementIcon() { return measurementIcon; }
    public String getTitle() { return title; }
    public String getAddress() { return address; }
    public String getType() { return type; }
    public String getSize() { return size; }

    // ✅ Additional getters for clarity
    public String getProjectName() { return title; }
    public String getFlatType() { return type; }
    public String getMeasurement() { return size; }

    // ✅ Parcelable Implementation
    protected SitesListItem(Parcel in) {
        imageList = new ArrayList<>();
        in.readList(imageList, Integer.class.getClassLoader());
        locationIcon = in.readInt();
        buildingIcon = in.readInt();
        measurementIcon = in.readInt();
        title = in.readString();
        address = in.readString();
        type = in.readString();
        size = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(imageList);
        dest.writeInt(locationIcon);
        dest.writeInt(buildingIcon);
        dest.writeInt(measurementIcon);
        dest.writeString(title);
        dest.writeString(address);
        dest.writeString(type);
        dest.writeString(size);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SitesListItem> CREATOR = new Creator<SitesListItem>() {
        @Override
        public SitesListItem createFromParcel(Parcel in) {
            return new SitesListItem(in);
        }

        @Override
        public SitesListItem[] newArray(int size) {
            return new SitesListItem[size];
        }
    };

    public int getImageRes() {
        return 0;
    }
}
