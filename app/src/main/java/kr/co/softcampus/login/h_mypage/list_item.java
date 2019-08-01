package kr.co.softcampus.login.h_mypage;

import java.util.Date;

public class list_item {
    public Date getWrite_date() {
        return write_date;
    }

    public void setWrite_date(Date write_date) {
        this.write_date = write_date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    private Date write_date;
    private String note;

    public list_item(Date write_date, String note, String get) {
        this.write_date = write_date;
        this.note = note;
        this.get = get;
    }

    private String get;
}


