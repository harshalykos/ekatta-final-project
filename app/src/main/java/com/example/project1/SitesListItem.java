package com.example.project1;

import android.os.Parcel;
import android.os.Parcelable;

public class SitesListItem implements Parcelable {
    private int imageRes, locationIcon, buildingIcon, measurementIcon;
    private String title, address, type, size;

    public SitesListItem(int imageRes, int locationIcon, int buildingIcon, int measurementIcon,
                         String title, String address, String type, String size) {
        this.imageRes = imageRes;
        this.locationIcon = locationIcon;
        this.buildingIcon = buildingIcon;
        this.measurementIcon = measurementIcon;
        this.title = title;
        this.address = address;
        this.type = type;
        this.size = size;
    }

    protected SitesListItem(Parcel in) {
        imageRes = in.readInt();
        locationIcon = in.readInt();
        buildingIcon = in.readInt();
        measurementIcon = in.readInt();
        title = in.readString();
        address = in.readString();
        type = in.readString();
        size = in.readString();
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

    public int getImageRes() { return imageRes; }
    public String getTitle() { return title; }
    public String getAddress() { return address; }
    public String getType() { return type; }
    public String getSize() { return size; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageRes);
        parcel.writeInt(locationIcon);
        parcel.writeInt(buildingIcon);
        parcel.writeInt(measurementIcon);
        parcel.writeString(title);
        parcel.writeString(address);
        parcel.writeString(type);
        parcel.writeString(size);
    }
}
