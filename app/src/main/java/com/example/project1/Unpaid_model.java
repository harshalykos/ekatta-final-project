package com.example.project1;

public class Unpaid_model {
    private int profileImage;
    private String name;
    private String details;
    private String dept;
    private String wing;
    private String flat;
    private int statusIcon;
    private String statusText;

    public Unpaid_model(int profileImage, String name, String details, String dept,
                        String wing, String flat, int statusIcon, String statusText) {
        this.profileImage = profileImage;
        this.name = name;
        this.details = details;
        this.dept = dept;
        this.wing = wing;
        this.flat = flat;
        this.statusIcon = statusIcon;
        this.statusText = statusText;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public String getDept() {
        return dept;
    }

    public String getWing() {
        return wing;
    }

    public String getFlat() {
        return flat;
    }

    public int getStatusIcon() {
        return statusIcon;
    }

    public String getStatusText() {
        return statusText;
    }
}
