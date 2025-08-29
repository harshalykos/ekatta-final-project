package com.example.project1;

public class ReqModel {
    private String purpose;
    private String amount;
    private String date;
    private String daysAgo;
    private int statusIcon;

    public ReqModel(String purpose, String amount, String date, String daysAgo, int statusIcon) {
        this.purpose = purpose;
        this.amount = amount;
        this.date = date;
        this.daysAgo = daysAgo;
        this.statusIcon = statusIcon;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getDaysAgo() {
        return daysAgo;
    }

    public int getStatusIcon() {
        return statusIcon;
    }
}
