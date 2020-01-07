package kr.co.softcampus.login.h_mypage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.softcampus.login.Connection.Constant;


/**
 * @name list_item
 * @descriptions transaction 자료형
 */

public class list_item {

    private Date write_date;
    private String date_string;
    private String note;
    private String from, to;
    private String isSucceed;
    private double amount;
    private String tx;

    SimpleDateFormat simpleDateFormat;

    public list_item(String date_string, String note, String from, String to, double amount, String isSucceed) throws ParseException {
        this.date_string = date_string;
        this.note = note;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.isSucceed = isSucceed;

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        write_date = simpleDateFormat.parse(date_string.substring(0,10)+ " " + date_string.substring(12,20));

        if(from.equals(Constant.WADDRESS)) {
            tx = to + "에게";
        } else {
            tx = from + "로부터";
        }
    }


    public void setDate_string(String date_string) throws ParseException {
        this.date_string = date_string;
        write_date = simpleDateFormat.parse(date_string.substring(0,10) + " " + date_string.substring(12,20));
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getIsSucceed() {
        return isSucceed;
    }

    public void setIsSucceed(String isSucceed) {
        this.isSucceed = isSucceed;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getWrite_date() {
        simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        return simpleDateFormat.format(write_date);
    }

    public String getTx() {
        return tx;
    }
}


