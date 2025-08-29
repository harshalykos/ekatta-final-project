package com.example.project1;

public class HistoryItem {
    private String purpose;
    private String date;
    private String daysAgo;
    private String amount;
    private int iconResId;

    public HistoryItem(String purpose, String date, String daysAgo, String amount, int iconResId) {
        this.purpose = purpose;
        this.date = date;
        this.daysAgo = daysAgo;
        this.amount = amount;
        this.iconResId = iconResId;
    }

    public String getPurpose() { return purpose; }
    public String getDate() { return date; }
    public String getDaysAgo() { return daysAgo; }
    public String getAmount() { return amount; }
    public int getIconResId() { return iconResId; }
}
