package com.example.project1;

public class Society_list_item {
    private int societyUserDp;
    private String societyUsername;
    private String societyMobile;

    public Society_list_item(int societyUserDp, String societyUsername, String societyMobile) {
        this.societyUserDp = societyUserDp;
        this.societyUsername = societyUsername;
        this.societyMobile = societyMobile;
    }

    public int getSocietyUserDp() {
        return societyUserDp;
    }

    public void setSocietyUserDp(int societyUserDp) {
        this.societyUserDp = societyUserDp;
    }

    public String getSocietyUsername() {
        return societyUsername;
    }

    public void setSocietyUsername(String societyUsername) {
        this.societyUsername = societyUsername;
    }

    public String getSocietyMobile() {
        return societyMobile;
    }

    public void setSocietyMobile(String societyMobile) {
        this.societyMobile = societyMobile;
    }
}
