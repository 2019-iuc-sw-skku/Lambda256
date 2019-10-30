package kr.co.softcampus.login.i_send;

import android.widget.ImageView;

public class sendlist_item {
    private String address;
    private double token;

    public sendlist_item(String address, double token){
        this.address = address;
        this.token = token;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getToken() {
        return token;
    }

    public void setToken(double token) {
        this.token = token;
    }
}
