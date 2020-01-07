package kr.co.softcampus.login.i_send;

/**
 * @name sendlist_item
 * @descriptions 송금 자료형
 */

public class sendlist_item {
    private String address;
    private long token;

    public sendlist_item(String address, long token){
        this.address = address;
        this.token = token;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }
}
