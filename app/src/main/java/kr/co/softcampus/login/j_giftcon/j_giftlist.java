package kr.co.softcampus.login.j_giftcon;

import android.widget.LinearLayout;

public class j_giftlist {
    private String name, category1, catergory2;
    private int cost, count;

    public j_giftlist(String name, String category1, String catergory2, int cost, int count) {
        this.name = name;
        this.category1 = category1;
        this.catergory2 = catergory2;
        this.cost = cost;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCatergory2() {
        return catergory2;
    }

    public void setCatergory2(String catergory2) {
        this.catergory2 = catergory2;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
