package com.example.project1;

public class CardItem {
    private String date;
    private String serviceNo;
    private String serviceCategory;
    private String status;
    private String description;
    private int statusIconResId;
    private int imageResId;

    public CardItem(String date, String serviceNo, String serviceCategory, String status, String description, int statusIconResId, int imageResId) {
        this.date = date;
        this.serviceNo = serviceNo;
        this.serviceCategory = serviceCategory;
        this.status = status;
        this.description = description;
        this.statusIconResId = statusIconResId;
        this.imageResId = imageResId;
    }

    public String getDate() { return date; }
    public String getServiceNo() { return serviceNo; }
    public String getServiceCategory() { return serviceCategory; }
    public String getStatus() { return status; }
    public String getDescription() { return description; }
    public int getStatusIconResId() { return statusIconResId; }
    public int getImageResId() { return imageResId; }
}
